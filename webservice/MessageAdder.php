<?php
error_reporting(E_ALL);
header('Content-type: application/json');
require("connect.php");
require("AuthenticationHelper.php");
$from = $_REQUEST['from'];
$to = $_REQUEST['to'];
$password = $_REQUEST['password'];
$message = $_REQUEST['message'];

$data = array( 'error' => true );
if(!empty($from) && !empty($to) && !empty($password) && !empty($message))
{
  $from_id = authenticate($from, $password);
  $to_id = get_user_id($to);

  if ($from_id != -1 && $to_id != -1)
  {
    $query = "INSERT INTO message ( id_user_sender,id_user_receiver, text, message_type_id)
	  VALUES ('$from_id','$to_id', '$message', 'USERM')";

    $result = MYSQL_QUERY($query);

    $data = array( 'error' => false );

  }

}
echo json_encode( $data );
require("disconnect.php");
?>