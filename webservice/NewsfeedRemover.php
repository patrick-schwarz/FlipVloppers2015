<?php
error_reporting(E_ALL);
header('Content-type: application/json');
require("connect.php");
require("AuthenticationHelper.php");
$user = $_REQUEST['user'];
$password = $_REQUEST['password'];
$message = $_REQUEST['message'];

$data = array( 'error' => true );
if(!empty($user) && !empty($password) && !empty($message))
{
  $user_id = authenticate($user, $password);

  if ($user_id != -1)
  {
    $query = "DELETE FROM message WHERE text LIKE '$message' AND id_user_sender = '$user_id'";

    $result = MYSQL_QUERY($query);

    $data = array( 'error' => false );

  }

}
echo json_encode( $data );
require("disconnect.php");
?>