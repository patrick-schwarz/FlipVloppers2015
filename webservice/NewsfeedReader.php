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
    $query = "SELECT * FROM message m INNER JOIN user u ON (m.id_user_sender = u.iduser) WHERE message_type_id='NEWSF' ORDER BY create_time DESC";
  
  	MYSQL_QUERY("SET NAMES 'utf8'");
    $result = MYSQL_QUERY($query);
	$number = MYSQL_NUMROWS($result);

	if ($number != 0)
    {
	  $output_array = array();
	  for ($i = 0; $i < $number; $i++) 
      {        
        $id = mysql_result($result, $i, "idmessage");
        $user = mysql_result($result, $i, "username");
		$first = mysql_result($result, $i, "first_name");
		$last = mysql_result($result, $i, "last_name");
		$date = mysql_result($result, $i, "create_time");
		$text = mysql_result($result, $i, "text");
        $data = array( 'id' => $id, 'user' => $user, 'first' => $first, 'last' => $last, 'date' => $date, 'message' => $text );
        array_push($output_array, $data);
      }
	  echo json_encode( $output_array );

    }
  }
  
}

require("disconnect.php");
?>