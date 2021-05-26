<?php
// required headers
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Headers: access");
header("Access-Control-Allow-Methods: GET");
header("Access-Control-Allow-Credentials: true");
header('Content-Type: application/json');
  
// include database and object files
include_once '../config/database.php';
include_once '../objects/opdrachtgever.php';
  
// get database connection
$database = new Database();
$db = $database->getConnection();
  
// prepare opdrachtgever object
$opdrachtgever = new Opdrachtgever($db);
  
// set ID property of record to read
$opdrachtgever->id = isset($_GET['id']) ? $_GET['id'] : die();
  
// read the details of opdrachtgever to be edited
$opdrachtgever->readOne();
  
if($opdrachtgever->naam!=null){
    // create array
    $opdrachtgever_arr = array(
        "id" =>  $opdrachtgever->id,
        "naam" => $opdrachtgever->naam,
        "adres" => $opdrachtgever->adres,
        "postcode" => $opdrachtgever->postcode,
        "gemeente" => $opdrachtgever->gemeente,
        "email" => $opdrachtgever->email,
        "klantNaam" => $opdrachtgever->klantNaam,
        "projectId" => $opdrachtgever->projectId
    );
  
    // set response code - 200 OK
    http_response_code(200);
  
    // make it json format
    echo json_encode($opdrachtgever_arr);
}
  
else{
    // set response code - 404 Not found
    http_response_code(404);
  
    // tell the user opdrachtgever does not exist
    echo json_encode(array("message" => "opdrachtgever does not exist."));
}
?>