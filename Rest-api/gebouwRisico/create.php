<?php
// required headers
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: POST");
header("Access-Control-Max-Age: 3600");
header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
  
// get database connection
include_once '../config/database.php';
  
// instantiate gebouwrisico object
include_once '../objects/gebouwRisico.php';
  
$database = new Database();
$db = $database->getConnection();
  
$gebouwrisico = new Gebouwrisico($db);
  
// get posted data
$data = json_decode(file_get_contents("php://input"));
  
// make sure data is not empty
if(
    !empty($data->beschrijving) &&
    !empty($data->w)
){
  
    // set gebouwrisico property values
    $gebouwrisico->beschrijving = $data->beschrijving;
    $gebouwrisico->w = $data->w;
  
    // create the gebouwrisico
    if($gebouwrisico->create()){
  
        // set response code - 201 created
        http_response_code(201);
  
        // tell the user
        echo json_encode(array("message" => "gebouwrisico was created."));
    }
  
    // if unable to create the gebouwrisico, tell the user
    else{
  
        // set response code - 503 service unavailable
        http_response_code(503);
  
        // tell the user
        echo json_encode(array("message" => "Unable to create gebouwrisico."));
    }
}
  
// tell the user data is incomplete
else{
  
    // set response code - 400 bad request
    http_response_code(400);
  
    // tell the user
    echo json_encode(array("message" => "Unable to create gebouwrisico. Data is incomplete."));
}
?>