<?php
include '../db/db_connect.php';

//Get the input request parameters
$response = array();
$response["items"] = array();

$query = mysqli_query($con, "SELECT * FROM items");

while ($row = mysqli_fetch_array($query, MYSQLI_ASSOC)) {
    $t = array();
    $t["itemId"] = $row["item_ids"];
    $t["itemName"] = $row["item_name"];
    $t["itemImage"] = $row["item_image"];
    $t["itemPrice"] = $row["item_price"];
    $t["itemBio"] = $row["item_bio"];
    $t["itemReview"] = $row["item_review"];

    // Mảng JSON
    array_push($response["items"], $t);
}
// Hiển thị kết quả
echo json_encode($response, \JSON_UNESCAPED_UNICODE);
