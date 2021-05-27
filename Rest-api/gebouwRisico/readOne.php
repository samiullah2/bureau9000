<?php
// required headers
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Headers: access");
header("Access-Control-Allow-Methods: GET");
header("Access-Control-Allow-Credentials: true");
header('Content-Type: application/json');
  
// include database and object files
include_once '../config/database.php';
include_once '../objects/gebouwRisico.php';
  
// get database connection
$database = new Database();
$db = $database->getConnection();
  
// prepare gebouwRisico object
$gebouwRisico = new GebouwRisico($db);
  
// set ID property of record to read
$gebouwRisico->id = isset($_GET['id']) ? $_GET['id'] : die();
  
// read the details of gebouwRisico to be edited
$gebouwRisico->readOne();
  
if($gebouwRisico->beschrijving!=null){
    // create array
    $gebouwRisico_arr = array(
        "id" =>  $gebouwRisico->id,
        "beschrijving" => $gebouwRisico->beschrijving,
        "w" => $gebouwRisico->w

    );
  
    // set response code - 200 OK
    http_response_code(200);
  
    // make it json format
    echo json_encode($gebouwRisico_arr);
}
  
else{
    // set response code - 404 Not found
    http_response_code(404);
  
    // tell the user gebouwRisico does not exist
    echo json_encode(array("message" => "gebouwRisico does not exist."));
}
?>