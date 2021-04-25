<?php
class Risicobeschrijving{
  
    // database connection and table name
    private $conn;
    private $table_name = "risicobeschrijving";
  
    // object properties
    public $id;
    public $beschrijving;
    public $waarde;
    
  
    // constructor with $db as database connection
    public function __construct($db){
        $this->conn = $db;
    }

    // read risicobeschrijvingen
function read(){

  
    //select all query
       $query = 
       "SELECT id, beschrijving, waarde
        FROM risicobeschrijving ";

    // prepare query statement
    $stmt = $this->conn->prepare($query);
  
    // execute query
    $stmt->execute();
  
    return $stmt;
    }

    
    // used when you want a specific risicobeschrijving
    function readOne(){
  
    // query to read single record
    $query = "SELECT id, beschrijving, waarde
              FROM risicobeschrijving 
              WHERE id = ?
              LIMIT 0,1";
  
    // prepare query statement
    $stmt = $this->conn->prepare( $query );
  
    // bind id of risicobeschrijving
    $stmt->bindParam(1, $this->id);
  
    // execute query
    $stmt->execute();
  
    // get retrieved row
    $row = $stmt->fetch(PDO::FETCH_ASSOC);
  
    // set values to object properties
    $this->beschrijving = $row['beschrijving'];
    $this->waarde = $row['waarde'];
    }

    


}
?>