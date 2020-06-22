<?php
include '../db/db_connect.php';
header('Content-Type:text/html; charset=UTF-8');
$response = array();

//Get the input request parameters
$inputJSON = file_get_contents('php://input');
$input = json_decode($inputJSON, TRUE); //convert JSON into array


if (isset($_POST['userName'])) {
    $userName = $_POST['userName'];
    $userFullName = $_POST['userFullName'];
    $userEmail = $_POST['userEmail'];
    $userPhone = $_POST['userPhone'];
    $userAddress = $_POST['userAddress'];
    $userBio = $_POST['userBio'];
    $userGender = $_POST['userGender'];
    $ServerURL = null;
    if (isset($_POST['userImage'])) {
        $userImageName = $_POST['userImageName'];
        $userImage = $_POST['userImage'];
        $ImagePath = "userImage/$userImageName.jpg";
        $ServerURL = "http://192.168.1.7/DoAn_Android_2020/users/$ImagePath";
    }

    $editQuery = "UPDATE member SET  user_image = '$ServerURL' ,user_fullname = '$userFullName' , user_email = '$userEmail' ,user_phone = '$userPhone' ,
                user_address = '$userAddress' ,user_bio = '$userBio' ,user_gender = '$userGender' WHERE user_name = ?";

    if ($stmt = $con->prepare($editQuery)) {
        $stmt->bind_param("s", $userName);
        $stmt->execute();
        if (isset($userImage)) {
            file_put_contents($ImagePath, base64_decode($userImage));
        }
        $response["status"] = 0;
        $response["message"] = "Edit successful";
        $stmt->close();
    } else {
        $response["status"] = 1;
        $response["message"] = "Edit error";
    }
} else {
    $response["status"] = 2;
    $response["message"] = "Missing mandatory parameters";
}
echo json_encode($response, \JSON_UNESCAPED_UNICODE);
