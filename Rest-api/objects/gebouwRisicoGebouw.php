<?php
class GebouwRisicoGebouw{
  
    // database connection and table name
    private $conn;
    private $table_name = "gebouwRisicoGebouw";
  
    // object properties
    public $id;
    public $gebouwId;
    public $gebouwRisicoId;
    
  
    // constructor with $db as database connection
    public function __construct($db){
        $this->conn = $db;
    }
    
    // read all products with specific ID
    function readByGebouwId($gebouwId){
  
        // select query
        $query = "SELECT id, gebouwId, gebouwRisicoId 
                    FROM gebouwRisicoGebouw
                    WHERE gebouwId = ?";
    
        // prepare query statement
        $stmt = $this->conn->prepare($query);
    
        // // sanitize
        // $projectId=htmlspecialchars(strip_tags($projectId));
        // $projectId = "%{$projectId}%";
    
        // bind
        $stmt->bindParam(1, $gebouwId);
    
        // execute query
        $stmt->execute();
  
    return $stmt;
}

    // create gebouwRisicoGebouw
    function create(){
  
    // query to insert record
    $query = "INSERT INTO gebouwRisicoGebouw
                SET gebouwId=:gebouwId, gebouwRisicoId=:gebouwRisicoId";
  
    // prepare query
    $stmt = $this->conn->prepare($query);
  
    // sanitize
    $this->gebouwId=htmlspecialchars(strip_tags($this->gebouwId));
    $this->gebouwRisicoId=htmlspecialchars(strip_tags($this->gebouwRisicoId));
  
    // bind values
    $stmt->bindParam(":gebouwId", $this->gebouwId);
    $stmt->bindParam(":gebouwRisicoId", $this->gebouwRisicoId);
  
    // execute query
    if($stmt->execute()){
        return true;
    }
  
    return false;
    }
}
?>