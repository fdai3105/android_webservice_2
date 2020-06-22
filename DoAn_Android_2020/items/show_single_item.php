<?php
include '../db/db_connect.php';
$response = array();

if (isset($_POST['itemIds'])) {
    $itemIds = $_POST['itemIds'];

    $query = mysqli_query($con, "SELECT * FROM items WHERE item_ids = $itemIds");
    while ($row = mysqli_fetch_array($query, MYSQLI_ASSOC)) {
        $response['itemName'] = $row['item_name'];
        $response['itemImage'] = $row['item_image'];
        $response['itemPrice'] = $row['item_price'];
        $response['itemBio'] = $row['item_bio'];
        $response['itemReview'] = $row['item_review'];

        $response["status"] = 0;
        $response["message"] = "Show single success";
    }
} else {
    $response["status"] = 2;
    $response["message"] = "Missing mandatory parameters";
}
echo json_encode($response, \JSON_UNESCAPED_UNICODE);
