<?php

	require_once 'connect.php';
    $response = array();

    $json = file_get_contents("php://input");   
	$json_data = json_decode($json, true);

	// if (isset($json_data[0]['user_name'])) {
	//     $user_name = $json_data[0]['user_name'];

	    $result = mysqli_query($conn, "SELECT * from category");

	    $response = array();
	    while ($row = mysqli_fetch_array($result)) {
	        $data = array();
	        $data["id"] = $row["c_id"];
	        $data["name"] = $row["c_name"];
  
       		array_push($response, $data);
    	}
	    // $response["success"] = 1;
	    echo json_encode($response);

?>