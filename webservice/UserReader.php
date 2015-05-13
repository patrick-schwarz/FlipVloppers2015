<?php
error_reporting(E_ALL);
header('Content-type: application/json');
require("connect.php");
require("AuthenticationHelper.php");
$user = $_REQUEST['user'];
$password = $_REQUEST['password'];

if(!empty($user) && !empty($password))
{	
  $user_id = authenticate($user, $password);

  if ($user_id != -1)
  {
    $query = "SELECT * FROM user u WHERE iduser != $user_id ORDER BY last_name, first_name";
  
    $result = MYSQL_QUERY($query);
	$number = MYSQL_NUMROWS($result);

	if ($number != 0)
    {
	  $output_array = array();
	  for ($i = 0; $i < $number; $i++) 
      {        
        $id = mysql_result($result, $i, "iduser");
        $user = mysql_result($result, $i, "username");
		$first = mysql_result($result, $i, "first_name");
		$last = mysql_result($result, $i, "last_name");
		$pw = mysql_result($result, $i, "password");
        $data = array( 'id_' => $id, 'username_' => $user, 'first_name_' => $first,
                        'last_name_' => $last, 'password_' => $pw);
        array_push($output_array, $data);
      }
	  echo json_encode( $output_array );

    }
  }
  
}

require("disconnect.php");
?>