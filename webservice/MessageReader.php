<?php
error_reporting(E_ALL);
header('Content-type: application/json');
require("connect.php");
require("AuthenticationHelper.php");
$to = $_REQUEST['to'];
$from = $_REQUEST['from'];
$password = $_REQUEST['password'];

if(!empty($to) && !empty($from) && !empty($password))
{
  $to_id = authenticate($to, $password);
  $from_id = get_user_id($from);

  if ($from_id != -1)
  {
    $query = "SELECT * FROM message m INNER JOIN user u ON (m.id_user_sender = u.iduser) WHERE message_type_id='USERM' AND ((m.id_user_receiver = $to_id) AND (m.id_user_sender=$from_id)) OR ((m.id_user_receiver=$from_id) AND (m.id_user_sender=$to_id)) ORDER BY create_time ASC";

    $result = MYSQL_QUERY($query);
	$number = MYSQL_NUMROWS($result);

	if ($number != 0)
    {
	  $output_array = array();
	  for ($i = 0; $i < $number; $i++)
      {
        $id = mysql_result($result, $i, "idmessage");
        $from_m = mysql_result($result, $i, "id_user_sender");
		$to_m = mysql_result($result, $i, "id_user_receiver");
		$date = mysql_result($result, $i, "create_time");
		$text = mysql_result($result, $i, "text");
        $data = array( 'id' => $id, 'from_id' => $from_m, 'to_id' => $to_m, 'date' => $date, 'message' => $text );
        array_push($output_array, $data);
      }
	  echo json_encode( $output_array );

    }
  }

}

require("disconnect.php");
?>