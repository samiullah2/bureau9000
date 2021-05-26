<?php
class Gebouw{
  
    // database connection and table name
    private $conn;
    private $table_name = "gebouw";
  
    // object properties
    public $id;
    public $naam;
    public $hoogte;
    public $projectId;
    public $risicobeschrijvingId;
    
  
    // constructor with $db as database connection
    public function __construct($db){
        $this->conn = $db;
    }

    // read gebouwen
    function read(){

  
    //select all query
       $query = 
       "SELECT id, naam, hoogte, projectId, risicobeschrijvingId
        FROM gebouw ";

    // prepare query statement
    $stmt = $this->conn->prepare($query);
  
    // execute query
    $stmt->execute();
  
    return $stmt;
    }

    
    // used when you want a specific gebouw
    function readOne(){
  
    // query to read single record
    $query = "SELECT id, naam, hoogte, projectId, risicobeschrijvingId
              FROM gebouw 
              WHERE id = ?
              LIMIT 0,1";
  
    // prepare query statement
    $stmt = $this->conn->prepare( $query );
  
    // bind id of gebouw
    $stmt->bindParam(1, $this->id);
  
    // execute query
    $stmt->execute();
  
    // get retrieved row
    $row = $stmt->fetch(PDO::FETCH_ASSOC);
  
    // set values to object properties
    $this->naam = $row['naam'];
    $this->hoogte = $row['hoogte'];
    $this->projectId = $row['projectId'];
    $this->projectId = $row['risicobeschrijvingId'];
    }

}
?>