<?php
// required headers
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
header("Access-Control-Allow-Methods: POST");
header("Access-Control-Max-Age: 3600");
header("Access-Control-Allow-Headers: Content-Type, Access-Control-Allow-Headers, Authorization, X-Requested-With");
  
// get database connection
include_once '../config/database.php';
  
// instantiate opdrachtgever object
include_once '../objects/opdrachtgever.php';
  
$database = new Database();
$db = $database->getConnection();
  
$opdrachtgever = new Opdrachtgever($db);
  
// get posted data
$data = json_decode(file_get_contents("php://input"));
  
// make sure data is not empty
if(
    !empty($data->naam) &&
    !empty($data->adres) &&
    !empty($data->postcode) &&
    !empty($data->gemeente) &&
    !empty($data->email) &&
    // !empty($data->klantNaam) && --> we don't want to check this one, because klantNaam can be empty !
    !empty($data->projectId)
){
    // set opdrachtgever property values
    $opdrachtgever->naam = $data->naam;
    $opdrachtgever->adres = $data->adres;
    $opdrachtgever->postcode = $data->postcode;
    $opdrachtgever->gemeente = $data->gemeente;
    $opdrachtgever->email = $data->email;
    $opdrachtgever->klantNaam = $data->klantNaam;
    $opdrachtgever->projectId = $data->projectId;
  
    // create the opdrachtgever
    if($opdrachtgever->create()){
  
        // set response code - 201 created
        http_response_code(201);
  
        // tell the user
        echo json_encode(array("message" => "opdrachtgever was created."));
    }
  
    // if unable to create the opdrachtgever, tell the user
    else{
  
        // set response code - 503 service unavailable
        http_response_code(503);
  
        // tell the user
        echo json_encode(array("message" => "Unable to create opdrachtgever."));
    }
}
  
// tell the user data is incomplete
else{
  
    // set response code - 400 bad request
    http_response_code(400);
  
    // tell the user
    echo json_encode(array("message" => "Unable to create opdrachtgever. Data is incomplete."));
}
?>