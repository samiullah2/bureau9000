<?php
// required headers
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");
  
// database connection will be here

// include database and object files
require '../config/database.php';
require '../objects/gebouw.php';
  
// instantiate database and gebouw object
$database = new Database();
$db = $database->getConnection();
  
// initialize object
$gebouw = new Gebouw($db);

// query gebouws
$stmt = $gebouw->read();
$num = $stmt->rowCount();
  
// check if more than 0 record found
if($num>0){
  
    // gebouws array
    $gebouws_arr=array();
  
    // retrieve our table contents
    // fetch() is faster than fetchAll()
    while ($row = $stmt->fetch(PDO::FETCH_ASSOC)){
        // extract row
        // this will make $row['name'] to
        // just $name only
        extract($row);
  
        $gebouw_item=array(
            "id" => $id,
            "naam" => $naam,
            "hoogte" => $hoogte,
            "projectId" => $projectId,
            "risicobeschrijvingId" => $risicobeschrijvingId
        );
  
        array_push($gebouws_arr, $gebouw_item);
    }
  
    // set response code - 200 OK
    http_response_code(200);
  
    // show gebouws data in json format
    echo json_encode($gebouws_arr);
}
  
// no gebouws found will be here

else{
  
  // set response code - 404 Not found
  http_response_code(404);

  // tell the user no gebouws found
  echo json_encode(
      array("message" => "No gebouws found.")
  );
}
