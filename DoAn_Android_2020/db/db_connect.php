<?php
define('DB_USER', "root"); // db user
define('DB_PASSWORD', ""); // db password (mention your db password here)
define('DB_DATABASE', "doan_android_2020"); // database name
define('DB_SERVER', "localhost"); // db server

$con = new mysqli(DB_SERVER, DB_USER, DB_PASSWORD, DB_DATABASE);

// Check connection
if ($con->connect_errno) {
	echo "Failed to connect to MySQL";
}
