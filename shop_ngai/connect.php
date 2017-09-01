<?php
    // $conn = mysqli_connect("localhost", "root", "", "tellme") or die('Could not connect');
$conn = new mysqli('localhost','root','','shop_ngai');
// $conn = new mysqli('mysql.hostinger.in.th','u204809019_tm','Mmay2535','u204809019_tm');
if ($conn->connect_errno) {
    die( "Failed to connect to MySQL : (" . $conn->connect_errno . ") " . $conn->connect_error);
}
$conn->set_charset("utf8");

?>