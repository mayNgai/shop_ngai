<?php

	require_once 'connect.php';
    $response = array();

    $json = file_get_contents("php://input");   
	$json_data = json_decode($json, true);

	// if (isset($json_data[0]['user_name'])) {
	//     $user_name = $json_data[0]['user_name'];

	    $result = mysqli_query($conn, "SELECT * from discount");

	    $response = array();
	    while ($row = mysqli_fetch_array($result)) {
	        $data = array();
	        $data["d_code"] = $row["d_code"];
	        $data["d_s_date"] = $row["d_s_date"];
	        $data["d_e_date"] = $row["d_e_date"];
	        $data["d_name_pic"] = $row["d_name_pic"];
  
       		array_push($response, $data);
    	}
	    // $response["success"] = 1;
	    echo json_encode($response);

?>