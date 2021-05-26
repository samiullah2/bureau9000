<?php
// required headers
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Headers: access");
header("Access-Control-Allow-Methods: GET");
header("Access-Control-Allow-Credentials: true");
header('Content-Type: application/json');
  
// include database and object files
include_once '../config/database.php';
include_once '../objects/kastRisico.php';
  
// get database connection
$database = new Database();
$db = $database->getConnection();
  
// prepare kastRisico object
$kastRisico = new KastRisico($db);
  
// set ID property of record to read
$kastRisico->id = isset($_GET['id']) ? $_GET['id'] : die();
  
// read the details of kastRisico to be edited
$kastRisico->readOne();
  
if($kastRisico->beschrijving!=null){
    // create array
    $kastRisico_arr = array(
        "id" =>  $kastRisico->id,
        "beschrijving" => $kastRisico->beschrijving,
        "w" => $kastRisico->w
    );
  
    // set response code - 200 OK
    http_response_code(200);
  
    // make it json format
    echo json_encode($kastRisico_arr);
}
  
else{
    // set response code - 404 Not found
    http_response_code(404);
  
    // tell the user kastRisico does not exist
    echo json_encode(array("message" => "kastRisico does not exist."));
}
?>