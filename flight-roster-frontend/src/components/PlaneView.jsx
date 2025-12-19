import React from 'react';

const PlaneView = ({ data }) => {
  if (!data) return <div>Loading...</div>;

  const { pilots, attendants, passengers, flightInfo } = data;

  // --- YARDIMCI FONKSİYONLAR ---
  
  // 1. Koltukta kim oturuyor bulalım
  const getOccupant = (seatNum) => {
    // Önce yolculara bak
    const passenger = passengers.find(p => p.seatNumber === seatNum);
    if (passenger) return { ...passenger, role: 'Passenger' };
    
    // Kabin ekibi genelde kapı önlerinde olur, biz görsel olsun diye
    // eğer veride koltuk numarası varsa onu kullanırız, yoksa manuel ekleriz.
    // Şimdilik sadece yolcuları koltuk numarasına göre oturtuyoruz.
    return null;
  };

  // 2. Hover (Üzerine gelince) çıkacak metin
  const getTooltip = (occupant, seatNum) => {
    if (!occupant) return `Seat ${seatNum}: Empty`;
    return `Seat: ${seatNum}\nName: ${occupant.name}\nType: ${occupant.seatType || occupant.role}\nAge: ${occupant.age}`;
  };

  // --- UÇAK DÜZENİ OLUŞTURUCU ---
  // Varsayılan: Airbus A320 (3 sol, 3 sağ)
  const totalSeats = flightInfo.vehicleType?.seatCount || 180;
  const seatsPerRow = 6; 
  const totalRows = Math.ceil(totalSeats / seatsPerRow);
  
  // Satırları oluştur (1, 2, 3...)
  const rows = Array.from({ length: totalRows }, (_, i) => i + 1);
  // Sütun harfleri (A,B,C -koridor- D,E,F)
  const leftSide = ['A', 'B', 'C'];
  const rightSide = ['D', 'E', 'F'];

  return (
    <div className="d-flex flex-column align-items-center">
      <h3 className="mb-4 text-center">Flight {flightInfo.flightNumber} Seating Plan</h3>
      
      <div className="plane-container">
        
        {/* 1. KOKPİT BÖLÜMÜ (PILOTS) */}
        <div className="cockpit-area">
          <h6 className="text-muted small mb-2">COCKPIT</h6>
          <div className="d-flex justify-content-center gap-3">
            {pilots.map((pilot, index) => (
              <div 
                key={index} 
                className="seat pilot" 
                title={`Captain: ${pilot.name}\n${pilot.seniorityLevel}`}
                style={{width: '60px'}}
              >
                P-{index+1}
              </div>
            ))}
             {pilots.length === 0 && <span className="small text-danger">No Pilots!</span>}
          </div>
        </div>

        {/* 2. KABİN EKİBİ (ÖN KISIM) */}
        <div className="d-flex justify-content-between px-4 mb-3">
            {attendants.slice(0, 2).map((att, i) => (
                 <div key={i} className="seat crew" title={`Crew: ${att.name}\n${att.type}`}>C{i+1}</div>
            ))}
        </div>

        {/* 3. YOLCU KOLTUKLARI (GÖVDE) */}
        <div className="cabin-body">
          {rows.map(rowNum => (
            <div key={rowNum} className="seat-row">
              {/* Sol Taraf (A, B, C) */}
              <div className="d-flex gap-1">
                {leftSide.map(char => {
                  const seatNum = `${rowNum}${char}`;
                  const occupant = getOccupant(seatNum);
                  return (
                    <div 
                      key={seatNum} 
                      className={`seat ${occupant ? 'occupied' : 'empty'}`}
                      title={getTooltip(occupant, seatNum)}
                    >
                      {seatNum}
                    </div>
                  );
                })}
              </div>

              {/* Koridor Numarası */}
              <div className="aisle-number">{rowNum}</div>

              {/* Sağ Taraf (D, E, F) */}
              <div className="d-flex gap-1">
                {rightSide.map(char => {
                  const seatNum = `${rowNum}${char}`;
                  const occupant = getOccupant(seatNum);
                  return (
                    <div 
                      key={seatNum} 
                      className={`seat ${occupant ? 'occupied' : 'empty'}`}
                      title={getTooltip(occupant, seatNum)}
                    >
                      {seatNum}
                    </div>
                  );
                })}
              </div>
            </div>
          ))}
        </div>

        {/* 4. KABİN EKİBİ (ARKA KISIM) */}
        <div className="d-flex justify-content-between px-4 mt-4 pt-3 border-top border-dashed">
            {attendants.slice(2).map((att, i) => (
                 <div key={i} className="seat crew" title={`Crew: ${att.name}\n${att.type}`}>C{i+3}</div>
            ))}
        </div>

      </div>

      {/* RENK AÇIKLAMALARI */}
      <div className="legend-box">
        <div className="legend-item"><div className="dot" style={{background:'#1e293b'}}></div> Pilot</div>
        <div className="legend-item"><div className="dot" style={{background:'#f59e0b'}}></div> Crew</div>
        <div className="legend-item"><div className="dot" style={{background:'#3b82f6'}}></div> Passenger</div>
        <div className="legend-item"><div className="dot" style={{background:'#e2e8f0'}}></div> Empty</div>
      </div>

    </div>
  );
};

export default PlaneView;