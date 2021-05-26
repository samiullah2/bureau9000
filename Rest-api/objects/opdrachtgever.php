<?php
class Opdrachtgever{
  
    // database connection and table name
    private $conn;
    private $table_name = "opdrachtgever";
  
    // object properties
    public $id;
    public $naam;
    public $adres;
    public $postcode;
    public $gemeente;
    public $email;
    public $klantNaam;
    public $projectId;
    
  
    // constructor with $db as database connection
    public function __construct($db){
        $this->conn = $db;
    }

    // read opdrachtgeveren
    function read(){

  
    //select all query
       $query = 
       "SELECT id, naam, adres, postcode, gemeente, email, klantNaam, projectId
        FROM opdrachtgever";

    // prepare query statement
    $stmt = $this->conn->prepare($query);
  
    // execute query
    $stmt->execute();
  
    return $stmt;
    }

    
    // used when you want a specific opdrachtgever
    function readOne(){
  
    // query to read single record
    $query = "SELECT id, naam, adres, postcode, gemeente, email, klantNaam, projectId
              FROM opdrachtgever 
              WHERE id = ?
              LIMIT 0,1";
  
    // prepare query statement
    $stmt = $this->conn->prepare( $query );
  
    // bind id of opdrachtgever
    $stmt->bindParam(1, $this->id);
  
    // execute query
    $stmt->execute();
  
    // get retrieved row
    $row = $stmt->fetch(PDO::FETCH_ASSOC);
  
    // set values to object properties
    $this->naam = $row['naam'];
    $this->naam = $row['adres'];
    $this->naam = $row['postcode'];
    $this->naam = $row['gemeente'];
    $this->naam = $row['email'];
    $this->naam = $row['klantNaam'];
    $this->naam = $row['projectId'];
    }

    // create opdrachtgever
    function create(){
  
    // query to insert record
    $query = "INSERT INTO opdrachtgever
                SET naam=:naam,adres=:adres,postcode=:postcode,gemeente=:gemeente,email=:email,klantNaam=:klantNaam,projectId=:projectId";
  
    // prepare query
    $stmt = $this->conn->prepare($query);
  
    // sanitize
    $this->naam=htmlspecialchars(strip_tags($this->naam));
    $this->adres=htmlspecialchars(strip_tags($this->adres));
    $this->postcode=htmlspecialchars(strip_tags($this->postcode));
    $this->gemeente=htmlspecialchars(strip_tags($this->gemeente));
    $this->email=htmlspecialchars(strip_tags($this->email));
    $this->klantNaam=htmlspecialchars(strip_tags($this->klantNaam));
    $this->projectId=htmlspecialchars(strip_tags($this->projectId));
  
    // bind values
    $stmt->bindParam(":naam", $this->naam);
    $stmt->bindParam(":adres", $this->adres);
    $stmt->bindParam(":postcode", $this->postcode);
    $stmt->bindParam(":gemeente", $this->gemeente);
    $stmt->bindParam(":email", $this->email);
    $stmt->bindParam(":klantNaam", $this->klantNaam);
    $stmt->bindParam(":projectId", $this->projectId);
  
    // execute query
    if($stmt->execute()){
        return true;
    }
  
    return false;
    }

}
?>