<?php
    require_once 'connect.php';
    
    $response = array();

    $json = file_get_contents("php://input");   
	$json_data = json_decode($json, true);

	if (isset($_POST['email'])&&isset($_POST['password'])) {
	    $email = $_POST['email'];
	    $password = $_POST['password'];

	    $result = mysqli_query($conn, "Select * From member Where m_email = '".$email."' AND m_password = '".$password."'");
    
	    $num_rows = mysqli_num_rows($result);

		if($num_rows>0){

			$response = array();
		    while ($row = mysqli_fetch_array($result)) {
		        $response["success"] = 1;
			    $response["message"] = "Login Success";
			    $response["first_name"] 	= $row["m_first_name"];
			    $response["last_name"]  	= $row["m_last_name"];
			    $response["status"] 		= $row["m_status"];
			    $response["tel"]  			= $row["m_tel"];
			    $response["email"] 			= $row["m_email"];
			    $response["date_register"]  = $row["m_date_create"];
			    $response["user_id"] 		= $row["m_user_id"];
			    $response["authentication"] = $row["m_authentication"];
			    $response["id"] 			= $row["m_id"];
	  
	        // push single product into final response array
	       		// array_push($response["details"], $detail);
	    	}
	    	
		    echo json_encode($response);
		}else{
			$response = array();
		    $response["success"] = 0;
		    $response["message"] = "Login fail";
	    	
		    echo json_encode($response);

		}

	    
	} else {
	    $response["success"] = 0;
	    $response["message"] = "error";
	  
	    echo json_encode($response);
}
?>