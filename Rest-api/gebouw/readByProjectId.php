<?php
// required headers
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
  
// include database and object files
include_once '../config/database.php';
include_once '../objects/gebouw.php';
  
// instantiate database and gebouw object
$database = new Database();
$db = $database->getConnection();
  
// initialize object
$gebouw = new Gebouw($db);
  
// get projectId
$projectId=isset($_GET["projectId"]) ? $_GET["projectId"] : die();
  
// query gebouws
$stmt = $gebouw->readByProjectId($projectId);
$num = $stmt->rowCount();
  
// check if more than 0 record found
if($num>0){
  
    // gebouws array
    $gebouws_arr=array();
  
    // retrieve our table contents
    // fetch() is faster than fetchAll()
    // http://stackoverflow.com/questions/2770630/pdofetchall-vs-pdofetch-in-a-loop
    while ($row = $stmt->fetch(PDO::FETCH_ASSOC)){
        // extract row
        // this will make $row['name'] to
        // just $name only
        extract($row);
  
        $gebouw_item=array(
            "id" => $id,
            "naam" => $naam,
            "adres" => $adres,
            "postcode" => $postcode,
            "gemeente" => $gemeente,
            "hoogte" => $hoogte,
            "functie" => $functie,
            "projectId" => $projectId,
        );
  
        array_push($gebouws_arr, $gebouw_item);
    }
  
    // set response code - 200 OK
    http_response_code(200);
  
    // show gebouws data
    echo json_encode($gebouws_arr);
}
  
else{
    // set response code - 404 Not found
    http_response_code(404);
  
    // tell the user no gebouws found
    echo json_encode(
        array("message" => "No gebouws found.")
    );
}
?>