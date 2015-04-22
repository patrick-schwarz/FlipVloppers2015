<?php
error_reporting(E_ALL);
header('Content-type: application/json');
require("connect.php");
require("AuthenticationHelper.php");
$mail = $_REQUEST['email'];
$password = $_REQUEST['password'];

$data = array( 'error' => true, 'id' => "-1", 'first_name' => " ", 'last_name' => " " );
if(!empty($mail) && !empty($password))
{	
  $user_id = authenticate($mail, $password);

  if ($user_id != -1)
  {
    $query = "SELECT * FROM user WHERE username=\"$mail\"";
  
    $result = MYSQL_QUERY($query);
    $first = mysql_result($result, 0, "first_name");
    $last = mysql_result($result, 0, "last_name");
    $data = array( 'error' => false, 'id' => "$user_id", 'first_name' => "$first", 'last_name' => "$last" );
   
  }
  
}
echo json_encode( $data );
require("disconnect.php");
?>