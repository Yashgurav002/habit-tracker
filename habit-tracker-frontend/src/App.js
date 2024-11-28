import './App.css';
import React, {useState, useEffect } from'react';

function App() {
  const [message,setMessage]=useState("");

  useEffect(()=>{
    fetch('http://localhost:8080/api/test')
  .then((response) => {
    if (!response.ok) {
      throw new Error(`HTTP error! status: ${response.status}`);
    }
    return response.text();
  })
  .then((data) => setMessage(data))
  .catch((error) => console.error('Error fetching data:', error));
  },[]);
  return (
    <div className="App">
      <h1>Habit Tracker</h1>
      <p>{message}</p>
    </div>
  );
}

export default App;
