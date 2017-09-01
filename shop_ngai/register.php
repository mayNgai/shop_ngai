<?php
    require_once 'connect.php';
    $response = array();
    
    $json = file_get_contents("php://input");   
	$json_data = json_decode($json, true);
	if (isset($json_data[0]['first_name'])) {
		$first_name 	= $json_data[0]['first_name'];
		$last_name 		= $json_data[0]['last_name'];
		$authentication = $json_data[0]['authentication'];
		$password 		= $json_data[0]['password'];
		$status 		= $json_data[0]['status'];
		$tel 			= $json_data[0]['tel'];
		$email 			= $json_data[0]['email'];
		$user_id 		= $json_data[0]['user_id'];

		

		date_default_timezone_set("Asia/Bangkok");
		$date = new DateTime("now", new DateTimeZone('Asia/Bangkok') );
		$date_register 	= $date->format('Y-m-d H:i:s');
		// $date_register 	= date("Y-m-d H:i:s");  
		$result;
		if($status == 0){
			$result = mysqli_query($conn, "SELECT * FROM member WHERE m_email = '".$email."'");
		}else if($status == 1){
			$result = mysqli_query($conn, "SELECT * FROM member WHERE m_user_id = '".$user_id."'");
		}
		
		$num_rows = mysqli_num_rows($result);

		if($num_rows>0){
			$response["success"] = 2;
	    	$response["details"] = array();
	    	while ($row = mysqli_fetch_array($result)) {
		        $detail = array();
		        $detail["first_name"] 		= $row["m_first_name"];
		        $detail["last_name"] 		= $row["m_last_name"];
		        $detail["user_id"] 			= $row["m_user_id"];
		        $detail["email"] 			= $row["m_email"];
		        $detail["tel"] 				= $row["m_tel"];
		        $detail["status"] 			= $row["m_status"];
		        $detail["date_create"] 		= $row["m_date_create"];
		        $detail["authentication"] 	= $row["m_authentication"];
	  
	        // push single product into final response array
	       		array_push($response["details"], $detail);
	    	}

	    	echo json_encode($response);

		}else{
			$result = mysqli_query($conn, "INSERT INTO member(m_last_name, m_first_name,m_date_create,m_user_id,m_email,m_tel,m_status,m_authentication,m_password) VALUES('".$last_name."', 
				'".$first_name."', '".$date_register."','".$user_id."', '".$email."', '".$tel."','".$status."', '".$authentication."', '".$password."')");

			if ($result) {
				$response["success"] = 1;
				$response["message"] = "successfully created.";
				 
				echo json_encode($response);
			} else {

				$response["success"] = 0;
				$response["message"] = "error";
				 
				echo json_encode($response);
			}

		}  
		


	}else{
		$response["success"] = 0;
	    $response["message"] = "error";
  
    // echoing JSON response
    echo json_encode($response);
	}

?>
