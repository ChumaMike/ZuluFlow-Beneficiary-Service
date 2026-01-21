import { useState } from 'react';
import Sidebar from './components/layout/Sidebar';
import DashboardHome from './modules/dashboard/DashboardHome';

function App() {
    const [activePage, setActivePage] = useState('dashboard');

    return (
        <div className="flex min-h-screen bg-brand-light">
            {/* 1. Left Sidebar Navigation */}
            <Sidebar activePage={activePage} setActivePage={setActivePage} />

            {/* 2. Main Content Area */}
            <main className="flex-1 ml-64 p-8">
                <div className="max-w-5xl mx-auto">
                    {/* Conditional Rendering based on menu selection */}
                    {activePage === 'dashboard' && <DashboardHome />}
                    {activePage === 'transact' && <div className="p-10 text-center text-gray-500">Transact Module Coming Soon... 💸</div>}
                    {activePage === 'vas' && <div className="p-10 text-center text-gray-500">VAS Module Coming Soon... ⚡</div>}
                    {activePage === 'profile' && <div className="p-10 text-center text-gray-500">Profile Module Coming Soon... 👤</div>}
                </div>
            </main>
        </div>
    );
}

export default App;