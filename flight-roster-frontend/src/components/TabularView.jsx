import React from 'react';

const TabularView = ({ data }) => {
  if (!data) return <div>Loading...</div>;

  const { pilots, attendants, passengers, flightInfo } = data;

  // --- VERİLERİ BİRLEŞTİRME (NORMALIZATION) ---
  // Herkesi tek bir "Liste Elemanı" formatına çeviriyoruz.

  const allRecords = [
    // 1. PİLOTLAR
    ...(pilots || []).map(p => ({
      id: `pilot-${p.pilotId}`,
      name: p.name,
      category: 'Flight Deck',
      role: p.seniorityLevel === 'Senior' ? 'Captain' : 'First Officer',
      detail: p.nationality,
      badgeColor: 'bg-dark'
    })),

    // 2. KABİN EKİBİ
    ...(attendants || []).map(a => ({
      id: `crew-${a.attendantId}`,
      name: a.name,
      category: 'Cabin Crew',
      role: a.type === 'Chef' ? 'Flying Chef' : 'Flight Attendant',
      detail: a.type, // Chief, Regular, Chef
      badgeColor: 'bg-warning text-dark'
    })),

    // 3. YOLCULAR
    ...(passengers || []).map(p => ({
      id: `pax-${p.passengerId}`,
      name: p.name,
      category: 'Passenger',
      role: p.seatType || 'Economy',
      detail: p.seatNumber || (p.age <= 2 ? 'Infant (Lap)' : 'Unassigned'),
      badgeColor: 'bg-primary'
    }))
  ];

  return (
    <div className="custom-card">
      <div className="d-flex justify-content-between align-items-center mb-3">
        <h5 className="card-title-custom mb-0">
            Full Flight Roster ({flightInfo.flightNumber})
        </h5>
        <span className="badge bg-secondary">Total: {allRecords.length} People</span>
      </div>

      <div className="table-responsive">
        <table className="custom-table table-hover">
          <thead>
            <tr>
              <th>#</th>
              <th>Name</th>
              <th>Category</th>
              <th>Role / Class</th>
              <th>Detail / Seat</th>
            </tr>
          </thead>
          <tbody>
            {allRecords.length > 0 ? (
              allRecords.map((item, index) => (
                <tr key={item.id}>
                  <td className="text-muted small">{index + 1}</td>
                  <td className="fw-bold">{item.name}</td>
                  <td>
                    <span className={`badge ${item.badgeColor} fw-normal`}>
                      {item.category}
                    </span>
                  </td>
                  <td>{item.role}</td>
                  <td className="font-monospace">{item.detail}</td>
                </tr>
              ))
            ) : (
              <tr>
                <td colSpan="5" className="text-center text-muted py-4">
                  No records found for this flight.
                </td>
              </tr>
            )}
          </tbody>
        </table>
      </div>
    </div>
  );
};

export default TabularView;