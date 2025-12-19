import { useState, useEffect } from 'react';
import 'bootstrap/dist/css/bootstrap.min.css';
import './App.css';
import { getAllFlights, getFlightRoster } from './api';
import ExtendedView from './components/ExtendedView';
import PlaneView from './components/PlaneView';
import TabularView from './components/TabularView';

function App() {
  const [flights, setFlights] = useState([]); 
  const [selectedFlightNum, setSelectedFlightNum] = useState(null);
  const [rosterData, setRosterData] = useState(null);
  const [loading, setLoading] = useState(false);
  const [viewMode, setViewMode] = useState('extended');

  useEffect(() => {
    getAllFlights()
      .then(res => {
        if (Array.isArray(res.data)) {
          setFlights(res.data);
        } else {
          setFlights([]); 
        }
      })
      .catch(err => {
        console.error("Hata:", err);
        setFlights([]); 
      });
  }, []);

  const handleSelectFlight = (flightNum) => {
    setSelectedFlightNum(flightNum);
    setLoading(true);
    
    getFlightRoster(flightNum)
      .then(res => {
        setRosterData(res.data);
        setLoading(false);
      })
      .catch(err => {
        console.error("Hata:", err);
        setLoading(false);
        setRosterData(null);
      });
  };

  const formatTime = (isoString) => {
    if(!isoString) return "";
    return new Date(isoString).toLocaleTimeString([], {hour: '2-digit', minute:'2-digit'});
  };

  return (
    /* BOOTSTRAP GRID YERİNE FLEX CONTAINER */
    <div className="app-container">
      
      {/* --- SOL MENÜ (Sabit 320px genişlik) --- */}
      <div className="sidebar">
        <div className="sidebar-header">
          <h4 className="mb-0 text-white fw-bold">✈️ FlightOps</h4>
          <small className="text-white" style={{opacity:0.7}}>Roster Management</small>
        </div>
        
        {Array.isArray(flights) && flights.length > 0 ? (
          flights.map(f => (
            <div 
              key={f.flightNumber} 
              className={`flight-item ${selectedFlightNum === f.flightNumber ? 'active' : ''}`}
              onClick={() => handleSelectFlight(f.flightNumber)}
            >
              <div className="d-flex justify-content-between align-items-center">
                 <span className="flight-code">{f.flightNumber}</span>
              </div>
              <div className="flight-route">
                {f.sourceAirport?.code || '?'} ➝ {f.destinationAirport?.code || '?'}
              </div>
              <div className="flight-time">
                🕒 {formatTime(f.departureDateTime)} • {f.vehicleType?.modelName || 'Jet'}
              </div>
            </div>
          ))
        ) : (
          <div className="p-4 text-center text-muted small">
             {flights ? "No flights found." : "Loading..."}
          </div>
        )}
      </div>

      {/* --- SAĞ İÇERİK (Kalan alanı doldurur) --- */}
      <div className="dashboard-content">
        {loading ? (
           <div className="d-flex flex-column justify-content-center align-items-center h-100">
             <div className="spinner-border text-primary" style={{width: '3rem', height: '3rem'}} role="status"></div>
             <span className="mt-3 text-muted fw-bold">Loading Flight Data...</span>
           </div>
        ) : rosterData ? (
          <>
            <ul className="nav nav-tabs mb-4 border-bottom-0">
              <li className="nav-item">
                  <button className={`nav-link ${viewMode === 'tabular' ? 'active' : ''}`} onClick={()=>setViewMode('tabular')}>Tabular</button>
              </li>
              <li className="nav-item">
                  <button className={`nav-link ${viewMode === 'plane' ? 'active fw-bold' : ''}`} onClick={()=>setViewMode('plane')}>Plane View</button>
              </li>
              <li className="nav-item">
                  <button className={`nav-link ${viewMode === 'extended' ? 'active fw-bold' : ''}`} onClick={()=>setViewMode('extended')}>Extended</button>
              </li>
            </ul>

            {viewMode === 'extended' ? (
                <ExtendedView data={rosterData} />
            ) : viewMode === 'plane' ? (
                <PlaneView data={rosterData} />
            ) : (
                <TabularView data={rosterData} />
            )}
          </>
        ) : (
           <div className="d-flex flex-column justify-content-center align-items-center h-100 text-muted">
             <div className="text-center p-5">
                <h1 className="display-4 fw-bold mb-3">👋 Welcome Back</h1>
                <p className="lead">Select a flight from the left sidebar to start managing the roster.</p>
             </div>
           </div>
        )}
      </div>
    </div>
  );
}

export default App;