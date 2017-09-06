<?php
    require_once 'connect.php';
    
    $response = array();

    $json = file_get_contents("php://input");   
	$json_data = json_decode($json, true);

	if (isset($_POST['first_name'])&&isset($_POST['last_name'])&&isset($_POST['tel'])&&isset($_POST['email'])&&isset($_POST['id'])) {
	    $first_name = $_POST['first_name'];
	    $last_name = $_POST['last_name'];
	    $tel = $_POST['tel'];
	    $email = $_POST['email'];
	    $id = $_POST['id'];

		$result = mysqli_query($conn, "UPDATE member SET m_first_name = '".$first_name."' , m_last_name = '".$last_name."' , m_email = '".$email."' , 
			m_tel = '".$tel."' WHERE m_id = '".$id."'");

		if ($result) {

				$result2 = mysqli_query($conn, "Select * From member Where m_email = '".$email."' AND m_id = '".$id."'");
    
			    $num_rows = mysqli_num_rows($result2);

				if($num_rows>0){

					$response = array();
				    while ($row = mysqli_fetch_array($result2)) {
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
					    
			    	}
			    	
				    echo json_encode($response);
				}else{
					$response = array();
				    $response["success"] = 0;
				    $response["message"] = "edit fail";
			    	
				    echo json_encode($response);

				}
		} else {

				$response["success"] = 0;
				$response["message"] = "cannot edit profile.";
				 
				echo json_encode($response);
		}
	    
	} else {
	    $response["success"] = 0;
	    $response["message"] = "error.";
	  
	    echo json_encode($response);
}
?>