<?php
// required headers
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: POST");
header("Access-Control-Max-Age: 3600");
header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
  
// get database connection
include_once '../config/database.php';
  
// instantiate gebouw object
include_once '../objects/gebouw.php';
  
$database = new Database();
$db = $database->getConnection();
  
$gebouw = new Gebouw($db);
  
// get posted data
$data = json_decode(file_get_contents("php://input"));
  
// make sure data is not empty
if(
    !empty($data->naam) &&
    !empty($data->hoogte)&&
    !empty($data->adres) &&
    !empty($data->postcode) &&
    !empty($data->gemeente) &&
    !empty($data->functie) &&
    !empty($data->projectId)
){
  
    // set gebouw property values
    $gebouw->naam = $data->naam;
    $gebouw->hoogte = $data->hoogte;
    $gebouw->adres = $data->adres;
    $gebouw->postcode = $data->postcode;
    $gebouw->gemeente = $data->gemeente;
    $gebouw->functie = $data->functie;
    $gebouw->projectId = $data->projectId;
  
    // create the gebouw
    if($gebouw->create()){
  
        // set response code - 201 created
        http_response_code(201);
  
        // tell the user
        echo json_encode(array("message" => "gebouw was created."));
    }
  
    // if unable to create the gebouw, tell the user
    else{
  
        // set response code - 503 service unavailable
        http_response_code(503);
  
        // tell the user
        echo json_encode(array("message" => "Unable to create gebouw."));
    }
}
  
// tell the user data is incomplete
else{
  
    // set response code - 400 bad request
    http_response_code(400);
  
    // tell the user
    echo json_encode(array("message" => "Unable to create gebouw. Data is incomplete."));
}
?>