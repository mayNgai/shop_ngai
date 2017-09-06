<?php
    require_once 'connect.php';
    $response = array();
    
    $json = file_get_contents("php://input");   
	$json_data = json_decode($json, true);
	if (isset($_POST['first_name'])&&isset($_POST['last_name'])&&isset($_POST['authentication'])&&isset($_POST['password'])&&isset($_POST['status'])&&isset($_POST['tel'])&&isset($_POST['email'])&&isset($_POST['user_id'])) {
		$first_name 	= $_POST['first_name'];
		$last_name 		= $_POST['last_name'];
		$authentication = $_POST['authentication'];
		$password 		= $_POST['password'];
		$status 		= $_POST['status'];
		$tel 			= $_POST['tel'];
		$email 			= $_POST['email'];
		$user_id 		= $_POST['user_id'];

		date_default_timezone_set("Asia/Bangkok");
		$date = new DateTime("now", new DateTimeZone('Asia/Bangkok') );
		$date_register 	= $date->format('Y-m-d H:i:s');
		
		$result = mysqli_query($conn, "INSERT INTO member(m_last_name, m_first_name,m_date_create,m_user_id,m_email,m_tel,m_status,m_authentication,m_password) VALUES('".$last_name."', 
			'".$first_name."', '".$date_register."','".$user_id."', '".$email."', '".$tel."','".$status."', '".$authentication."', '".$password."')");

		if ($result) {

			$result = mysqli_query($conn, "Select * From member Where m_email = '".$email."' AND m_password = '".$password."'");
    
			$num_rows = mysqli_num_rows($result);

			if($num_rows>0){

				$response = array();
				while ($row = mysqli_fetch_array($result)) {
				    $response["success"] = 1;
					$response["message"] = "successfully created.";
					$response["first_name"] 	= $row["m_first_name"];
					$response["last_name"]  	= $row["m_last_name"];
					$response["status"] 		= $row["m_status"];
					$response["tel"]  			= $row["m_tel"];
					$response["email"] 			= $row["m_email"];
					$response["date_register"]  = $row["m_date_create"];
					$response["user_id"] 		= $row["m_user_id"];
					$response["authentication"] = $row["m_authentication"];
					$response["id"] 			= $row["m_id"];
			    }
			    	
				    echo json_encode($response);
			}else{
					$response = array();
				    $response["success"] = 0;
				    $response["message"] = "Register fail";
			    	
				    echo json_encode($response);

			}
		} else {

			$response["success"] = 0;
			$response["message"] = "error";
				 
			echo json_encode($response);
		}

	}else{
		$response["success"] = 0;
	    $response["message"] = "error";

    echo json_encode($response);
	}

?>
