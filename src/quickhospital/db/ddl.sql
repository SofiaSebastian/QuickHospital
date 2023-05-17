CREATE TABLE IF NOT EXISTS Hospitals( Id   INTEGER  PRIMARY KEY AUTOINCREMENT, Name     TEXT     NOT NULL,  Capacity  INTEGER  NOT NULL,  Location  TEXT    NOT NULL);
CREATE TABLE IF NOT EXISTS Specialities(Id   INTEGER  PRIMARY KEY AUTOINCREMENT, Name     TEXT     NOT NULL);
CREATE TABLE IF NOT EXISTS Doctors(Id   INTEGER  PRIMARY KEY AUTOINCREMENT,Name     TEXT     NOT NULL, ArrivalTime  DATETIME  NOT NULL, DepartureTime  DATETIME    NOT NULL,Hospital_Id INTEGER REFERENCES Hospitals(Id) ON UPDATE CASCADE ON DELETE SET NULL,Speciality_Id INTEGER REFERENCES Specialities(Id) ON UPDATE CASCADE ON DELETE SET NULL);
CREATE TABLE IF NOT EXISTS Patients(Id   INTEGER  PRIMARY KEY AUTOINCREMENT,Name     TEXT     NOT NULL,Speciality_Id INTEGER REFERENCES Specialities(Id) ON UPDATE CASCADE ON DELETE SET NULL);
CREATE TABLE IF NOT EXISTS Symptoms(Id   INTEGER  PRIMARY KEY AUTOINCREMENT,Name     TEXT     NOT NULL);
CREATE TABLE IF NOT EXISTS Administrator(Id  TEXT  PRIMARY KEY,Password TEXT);
CREATE TABLE IF NOT EXISTS WaitingLists(Id   INTEGER  PRIMARY KEY AUTOINCREMENT,Date  DATETIME  NOT NULL, Status  BOOLEAN  NOT NULL,Patient_Id INTEGER REFERENCES Patients(Id) ON UPDATE CASCADE ON DELETE SET NULL,Speciality_Id INTEGER REFERENCES Specialities(Id) ON UPDATE CASCADE ON DELETE SET NULL,Hospital_Id INTEGER REFERENCES Hospitals(Id) ON UPDATE CASCADE ON DELETE SET NULL);
CREATE TABLE IF NOT EXISTS Hospitals_Specialities(Hospital_Id INTEGER REFERENCES Hospitals(Id) ON UPDATE CASCADE ON DELETE SET NULL,Speciality_Id INTEGER REFERENCES Specialities(Id) ON UPDATE CASCADE ON DELETE SET NULL,PRIMARY KEY (Hospital_Id,Speciality_Id));					
CREATE TABLE IF NOT EXISTS Specialities_Symptoms(Symptoms_Id INTEGER REFERENCES Symptoms(Id) ON UPDATE CASCADE ON DELETE SET NULL,Speciality_Id INTEGER REFERENCES Specialities(Id) ON UPDATE CASCADE ON DELETE SET NULL,PRIMARY KEY (Speciality_Id,Symptoms_Id));
CREATE TABLE IF NOT EXISTS Patients_Symptoms(Symptoms_Id INTEGER REFERENCES Symptoms(Id) ON UPDATE CASCADE ON DELETE SET NULL,Patient_Id INTEGER REFERENCES Patients(Id) ON UPDATE CASCADE ON DELETE SET NULL,PRIMARY KEY (Patient_Id,Symptoms_Id));

