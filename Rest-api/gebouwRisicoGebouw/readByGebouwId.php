<?php
// required headers
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
  
// include database and object files
include_once '../config/database.php';
include_once '../objects/gebouwRisicoGebouw.php';
  
// instantiate database and gebouwRisicoGebouw object
$database = new Database();
$db = $database->getConnection();
  
// initialize object
$gebouwRisicoGebouw = new GebouwRisicoGebouw($db);
  
// get gebouwId
$gebouwId=isset($_GET["gebouwId"]) ? $_GET["gebouwId"] : die();
  
// query gebouwRisicoGebouws
$stmt = $gebouwRisicoGebouw->readByGebouwId($gebouwId);
$num = $stmt->rowCount();
  
// check if more than 0 record found
if($num>0){
  
    // gebouwRisicoGebouws array
    $gebouwRisicoGebouws_arr=array();
  
    // retrieve our table contents
    // fetch() is faster than fetchAll()
    // http://stackoverflow.com/questions/2770630/pdofetchall-vs-pdofetch-in-a-loop
    while ($row = $stmt->fetch(PDO::FETCH_ASSOC)){
        // extract row
        // this will make $row['name'] to
        // just $name only
        extract($row);
  
        $gebouwRisicoGebouw_item=array(
            "id" => $id,
            "gebouwId" => $gebouwId,
            "gebouwRisicoId" => $gebouwRisicoId
        );
  
        array_push($gebouwRisicoGebouws_arr, $gebouwRisicoGebouw_item);
    }
  
    // set response code - 200 OK
    http_response_code(200);
  
    // show gebouwRisicoGebouws data
    echo json_encode($gebouwRisicoGebouws_arr);
}
  
else{
    // set response code - 404 Not found
    http_response_code(404);
  
    // tell the user no gebouwRisicoGebouws found
    echo json_encode(
        array("message" => "No gebouwRisicoGebouws found.")
    );
}
?>