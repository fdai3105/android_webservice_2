<?php
$response = array();
include '../db/db_connect.php';
include '../users/functions_user.php';

// //Get the input request parameters
// $inputJSON = file_get_contents('php://input');
// $input = json_decode($inputJSON, TRUE); //convert JSON into array

//Check for Mandatory parameters
if (isset($_POST['userName']) && isset($_POST['userPassword'])) {
	$userName = $_POST['userName'];
	$userFullname = "Guest";
	$userPassword = $_POST['userPassword'];
	//Check if user already exist
	if (!userExists($userName)) {

		//Get a unique Salt
		$salt         = getSalt();

		//Generate a unique password Hash
		$passwordHash = password_hash(concatPasswordWithSalt($userPassword, $salt), PASSWORD_DEFAULT);

		//Query to register new user
		$insertQuery  = "INSERT INTO member(user_name,user_fullname, password_hash, salt) VALUES (?,?,?,?)";
		if ($stmt = $con->prepare($insertQuery)) {
			$stmt->bind_param("ssss", $userName, $userFullname, $passwordHash, $salt);
			$stmt->execute();
			$response["status"] = 0;
			$response["message"] = "User created";
			$stmt->close();
		}
	} else {
		$response["status"] = 1;
		$response["message"] = "User exists";
	}
} else {
	$response["status"] = 2;
	$response["message"] = "Missing mandatory parameters";
}
echo json_encode($response);
