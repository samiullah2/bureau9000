<?php
// required headers
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Headers: access");
header("Access-Control-Allow-Methods: GET");
header("Access-Control-Allow-Credentials: true");
header('Content-Type: application/json');
  
// include database and object files
include_once '../config/database.php';
include_once '../objects/gebouw.php';
  
// get database connection
$database = new Database();
$db = $database->getConnection();
  
// prepare gebouw object
$gebouw = new Gebouw($db);
  
// set ID property of record to read
$gebouw->id = isset($_GET['id']) ? $_GET['id'] : die();
  
// read the details of gebouw to be edited
$gebouw->readOne();
  
if($gebouw->naam!=null){
    // create array
    $gebouw_arr = array(
        "id" =>  $gebouw->id,
        "naam" => $gebouw->naam,
        "hoogte" => $gebouw->hoogte,
        "adres" => $gebouw->adres,
        "postcode" => $gebouw->postcode,
        "gemeente" => $gebouw->gemeente,
        "functie" => $gebouw->functie,
        "projectId" => $gebouw->projectId,
    );
  
    // set response code - 200 OK
    http_response_code(200);
  
    // make it json format
    echo json_encode($gebouw_arr);
}
  
else{
    // set response code - 404 Not found
    http_response_code(404);
  
    // tell the user gebouw does not exist
    echo json_encode(array("message" => "gebouw does not exist."));
}
?>