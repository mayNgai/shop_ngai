<?php

	require_once 'connect.php';
    $response = array();

    $json = file_get_contents("php://input");   
	$json_data = json_decode($json, true);

	if (isset($_POST['o_vendor_id'])) {
	    $id = $_POST['o_vendor_id'];

	    $result = mysqli_query($conn, "Select * From `order` Where o_vendor_id = '".$id."'");
	// $result = mysqli_query($conn, "Select * From `order` Where o_vendor_id = '1'");

	    $num_rows = mysqli_num_rows($result);

		if($num_rows>0){

			$response = array();
		    while ($row = mysqli_fetch_array($result)) {
		    	$data = array();
		        $data["success"] = 1;
			    $data["message"] = "Success";
			    $data["o_id"] 				= $row["o_id"];
			    $data["o_name"]  			= $row["o_name"];
			    $data["o_c_id"] 			= $row["o_c_id"];
			    $data["o_vendor_id"]  		= $row["o_vendor_id"];
			    $data["o_start_date"] 		= $row["o_start_date"];
			    $data["o_end_date"]  		= $row["o_end_date"];
			    $data["o_price"] 			= $row["o_price"];
			    $data["o_discount"] 		= $row["o_discount"];
			    $data["o_ratting"] 			= $row["o_ratting"];
			    $data["o_stock"]  			= $row["o_stock"];
			    $data["o_category"] 		= $row["o_category"];
			    $data["o_condition"] 		= $row["o_condition"];
			    $data["o_like"]  			= $row["o_like"];
			    $data["o_wholesale_price"] 	= $row["o_wholesale_price"];
			    $data["o_brand"]  			= $row["o_brand"];
			    $data["o_ship_from"] 		= $row["o_ship_from"];
			    $data["o_detail"] 			= $row["o_detail"];
			    $data["o_count_ratting"] 	= $row["o_count_ratting"];
			    $data["o_pic_name"] 		= $row["o_pic_name"];
	  
	  			array_push($response, $data);
	    	}
	    	
		    echo json_encode($response);
		}else{
			$response = array();
		    $response["success"] = 0;
		    $response["message"] = "No data";
	    	
		    echo json_encode($response);

		}

	} else {
	    $response["success"] = 0;
	    $response["message"] = "error";
	  
	    echo json_encode($response);
	}
?>