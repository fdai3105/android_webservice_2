<?php
header('Content-Type:text/html; charset=UTF-8');
include '../db/db_connect.php';

//Get the input request parameters
$response = array();
$response["user"] = array();
$t = array();

//Check for Mandatory parameters
if (isset($_POST['userName'])) {
    $userName = $_POST['userName'];

    $query    = "SELECT user_fullname, user_image ,user_email ,user_phone , user_address ,user_bio ,user_gender FROM member WHERE user_name = ?";
    if ($stmt = $con->prepare($query)) {
        $stmt->bind_param("s", $userName);
        $stmt->execute();
        $stmt->bind_result($userFullName, $userImage, $userEmail, $userPhone,  $userAddress, $userBio, $userGender);
        if ($stmt->fetch()) {
            $t["userFullName"] = $userFullName;
            $t["userImage"] = $userImage;
            $t["userEmail"] = $userEmail;
            $t["userPhone"] = $userPhone;
            $t["userAddress"] = $userAddress;
            $t["userBio"] = $userBio;
            $t["userGender"] = $userGender;


            $response["status"] = "0";
            $response["message"] = "Success";
        } else {
            $response["status"] = "1";
            $response["message"] = "Fetch error";
        }
        $stmt->close();
    }
} else {
    $response["status"] = 2;
    $response["message"] = "Missing mandatory parameters";
}
array_push($response["user"], $t);
// Hiển thị kết quả JSON dạng UTF-8
echo json_encode($response, \JSON_UNESCAPED_UNICODE);
