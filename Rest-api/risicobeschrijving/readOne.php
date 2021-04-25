<?php
// required headers
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Headers: access");
header("Access-Control-Allow-Methods: GET");
header("Access-Control-Allow-Credentials: true");
header('Content-Type: application/json');
  
// include database and object files
include_once '../config/database.php';
include_once '../objects/risicobeschrijving.php';
  
// get database connection
$database = new Database();
$db = $database->getConnection();
  
// prepare risicobeschrijving object
$risicobeschrijving = new Risicobeschrijving($db);
  
// set ID property of record to read
$risicobeschrijving->id = isset($_GET['id']) ? $_GET['id'] : die();
  
// read the details of risicobeschrijving to be edited
$risicobeschrijving->readOne();
  
if($risicobeschrijving->beschrijving!=null){
    // create array
    $risicobeschrijving_arr = array(
        "id" =>  $risicobeschrijving->id,
        "beschrijving" => $risicobeschrijving->beschrijving,
        "waarde" => $risicobeschrijving->waarde
    );
  
    // set response code - 200 OK
    http_response_code(200);
  
    // make it json format
    echo json_encode($risicobeschrijving_arr);
}
  
else{
    // set response code - 404 Not found
    http_response_code(404);
  
    // tell the user risicobeschrijving does not exist
    echo json_encode(array("message" => "risicobeschrijving does not exist."));
}
?>