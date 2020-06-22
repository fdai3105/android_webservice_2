<?php
header('Content-Type: text/html; charset=utf-8');
$response = array();
include '../db/db_connect.php';
include '../users/functions_user.php';
 
//Get the input request parameters
$inputJSON = file_get_contents('php://input');
$input = json_decode($inputJSON, TRUE); //convert JSON into array
 
//Check for Mandatory parameters
if(isset($_POST['userName']) && isset($_POST['userPassword'])){
	$userName = $_POST['userName'];
	$userPassword = $_POST['userPassword'];

	$query    = "SELECT user_fullname, password_hash, salt FROM member WHERE user_name = ?";
	if($stmt = $con->prepare($query)){
		$stmt->bind_param("s", $userName);
		$stmt->execute();
		$stmt->bind_result($fullName,$passwordHashDB,$salt);
		if($stmt->fetch()){
			//Validate the password
			if(password_verify(concatPasswordWithSalt($userPassword,$salt),$passwordHashDB)){
				$response["status"] = 0;
				$response["message"] = "Login successful";
				$response["fullName"] = $fullName;
			}
			else{
				$response["status"] = 1;
				$response["message"] = "Invalid username and password combination";
			}
		} else{
			$response["status"] = 777;
			$response["message"] = "Fetch error.";
		}
	
		$stmt->close();
	}
}
else{
	$response["status"] = 2;
	$response["message"] = "Missing mandatory parameters";
}
//Display the JSON response
// Hiển thị kết quả JSON dạng UTF-8
echo json_encode($response, \JSON_UNESCAPED_UNICODE);
