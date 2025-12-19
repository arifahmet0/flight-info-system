import axios from 'axios';

const API_BASE_URL = "http://localhost:8080/api";

export const apiClient = axios.create({
    baseURL: API_BASE_URL,
    headers: { "Content-Type": "application/json" },
});

// Sidebar için uçuş listesini çeker
export const getAllFlights = () => {
    return apiClient.get('/flights');
};

// Seçilen uçuşun detaylarını (Pilot, Yolcu, Ekip) çeker
export const getFlightRoster = (flightNumber, dbType = 'SQL') => {
    return apiClient.get(`/roster`, {
        params: { flightNumber, dbType }
    });
};