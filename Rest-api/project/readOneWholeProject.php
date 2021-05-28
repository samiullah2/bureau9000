<?php
// required headers
header("Access-Control-Allow-Origin: *");
header("Access-Control-Allow-Headers: access");
header("Access-Control-Allow-Methods: GET");
header("Access-Control-Allow-Credentials: true");
header('Content-Type: application/json');
  
// include database and object files
include_once '../config/database.php';
include_once '../objects/project.php';
  
// get database connection
$database = new Database();
$db = $database->getConnection();
  
// prepare project object
$project = new project($db);
  
// set ID property of record to read
$project->id = isset($_GET['id']) ? $_GET['id'] : die();
  
// read the details of project to be edited
$project->readOneWholeProject();
  
if($project->naam!=null &&
    $project->opdrachtgeverNaam!=null &&
    $project->adres!=null &&
    $project->postcode!=null &&
    $project->gemeente!=null &&
    $project->email!=null
    //$project->klantNaam!=null --> niet want klantNaam is soms leeg
    ){
    
    // create array
    $project_arr = array(
        "id" =>  $project->id,
        "naam" => $project->naam,
        "opdrachtgeverNaam" => $project->opdrachtgeverNaam,
        "adres" => $project->adres,
        "postcode" => $project->postcode,
        "gemeente" => $project->gemeente,
        "email" => $project->email,
        "klantNaam" => $project->klantNaam
    );
  
    // set response code - 200 OK
    http_response_code(200);
  
    // make it json format
    echo json_encode($project_arr);
}
  
else{
    // set response code - 404 Not found
    http_response_code(404);
  
    // tell the user project does not exist
    echo json_encode(array("message" => "project does not exist."));
}
?>