import React from 'react';

const ExtendedView = ({ data }) => {
  if (!data) return <div className="p-4">Loading data...</div>;

  const { pilots, attendants, passengers, flightInfo } = data;

  // --- İSTATİSTİK HESAPLAMALARI ---
  const totalPax = passengers.length;
  const infantCount = passengers.filter(p => p && p.age <= 2).length;
  const occupiedSeats = totalPax - infantCount;
  const totalSeats = flightInfo.vehicleType?.seatCount || 180;

  // --- MENÜ MANTIĞI ---
  const standardMenu = flightInfo.vehicleType?.standardMenu || "Standard Snack";
  
  // Uçaktaki şefleri bul
  const chefs = attendants.filter(a => a.type === 'Chef');
  
  // Şeflerin tariflerini topla (Eğer şef varsa)
  const chefSpecials = chefs.flatMap(chef => chef.recipes ? chef.recipes.map(r => r.recipeName) : []);

  return (
    <div>
      {/* Üst Başlık */}
      <div className="d-flex justify-content-between align-items-center mb-4">
        <div>
           <h4 className="mb-0 fw-bold text-dark">Flight: {flightInfo.flightNumber || 'N/A'}</h4>
           <p className="text-muted mb-0">
             {flightInfo.sourceAirport?.city} ➝ {flightInfo.destinationAirport?.city}
           </p>
        </div>
        <div>
            <span className="badge bg-white text-dark border px-3 py-2 shadow-sm">
                Vehicle: {flightInfo.vehicleType?.modelName}
            </span>
        </div>
      </div>

      <div className="row">
        {/* SOL KOLON */}
        <div className="col-lg-8">
          
          {/* FLIGHT DECK */}
          <div className="custom-card">
            <h5 className="card-title-custom">Flight Deck</h5>
            <table className="custom-table">
              <thead><tr><th>Name</th><th>Role</th><th>Seniority</th></tr></thead>
              <tbody>
                {pilots.length > 0 ? pilots.map((p, i) => (
                  <tr key={i}>
                    <td className="fw-bold">{p.name}</td>
                    <td>{p.seniorityLevel === 'Senior' ? 'Captain' : 'First Officer'}</td>
                    <td><span className="badge-role">{p.seniorityLevel}</span></td>
                  </tr>
                )) : <tr><td colSpan="3" className="text-center text-muted">No pilots assigned.</td></tr>}
              </tbody>
            </table>
          </div>

          {/* CABIN CREW */}
          <div className="custom-card">
            <h5 className="card-title-custom">Cabin Crew</h5>
            <table className="custom-table">
              <thead><tr><th>Name</th><th>Role</th><th>Rank/Type</th></tr></thead>
              <tbody>
                {attendants.length > 0 ? attendants.map((a, i) => (
                  <tr key={i}>
                    <td>{a.name}</td>
                    <td>{a.type === 'Chef' ? '👨‍🍳 Flying Chef' : 'Cabin Crew'}</td>
                    <td>{a.type}</td>
                  </tr>
                )) : <tr><td colSpan="3" className="text-center text-muted">No crew assigned.</td></tr>}
              </tbody>
            </table>
          </div>
        </div>

        {/* SAĞ KOLON */}
        <div className="col-lg-4">
          
          {/* --- MENU KARTI (DİNAMİK) --- */}
          <div className="custom-card bg-light border-warning">
            <h5 className="card-title-custom text-warning-emphasis">🍽️ In-Flight Menu</h5>
            
            {/* 1. Standart Menü */}
            <div className="mb-3">
              <small className="text-muted d-block text-uppercase fw-bold">Standard Service</small>
              <div className="d-flex align-items-center gap-2">
                <span>🥪</span>
                <span className="fw-medium">{standardMenu}</span>
              </div>
            </div>

            {/* 2. Şefin Özelleri (Varsa) */}
            {chefSpecials.length > 0 ? (
              <div>
                <small className="text-muted d-block text-uppercase fw-bold">Chef's Gourmet Specials</small>
                {chefSpecials.map((dish, idx) => (
                  <div key={idx} className="d-flex align-items-center gap-2 mt-1">
                    <span>🍝</span>
                    <span className="fw-bold text-dark">{dish}</span>
                  </div>
                ))}
                <div className="mt-2 small text-muted fst-italic">
                  *Served exclusively by Chef {chefs[0]?.name}
                </div>
              </div>
            ) : (
              <div className="small text-muted fst-italic border-top pt-2">
                No chef on board today. Standard catering applies.
              </div>
            )}
          </div>

          {/* MANIFEST */}
          <div className="custom-card">
            <h5 className="card-title-custom">Manifest Snapshot</h5>
            <div className="d-flex justify-content-between mb-2 small">
                <span className="text-muted">Total Passengers</span>
                <span className="fw-bold">{totalPax}</span>
            </div>
            <div className="d-flex justify-content-between mb-2 small">
                <span className="text-muted">Infants on board</span>
                <span className="fw-bold text-danger">{infantCount}</span>
            </div>
            <div className="d-flex justify-content-between small">
                <span className="text-muted">Occupied Seats</span>
                <span className="fw-bold">{occupiedSeats} / {totalSeats}</span>
            </div>
          </div>

        </div>
      </div>

      {/* YOLCU LİSTESİ */}
      <div className="custom-card mt-2">
        <h5 className="card-title-custom">Passenger Detail</h5>
        <div className="table-responsive">
            <table className="custom-table">
                <thead><tr><th>Name</th><th>Type</th><th>Age</th><th>Seat</th><th>Class</th></tr></thead>
                <tbody>
                {passengers.length > 0 ? passengers.map((p, i) => (
                    <tr key={i}>
                    <td>{p.name}</td>
                    <td>{p.age <= 2 ? 'Infant' : 'Adult'}</td>
                    <td>{p.age}</td>
                    <td>{p.seatNumber ? <span className="badge bg-secondary">{p.seatNumber}</span> : <span className="text-muted">Infant (No Seat)</span>}</td>
                    <td>{p.seatType || 'Economy'}</td>
                    </tr>
                )) : <tr><td colSpan="5" className="text-center text-muted">No passengers.</td></tr>}
                </tbody>
            </table>
        </div>
      </div>
    </div>
  );
};

export default ExtendedView;