import React from 'react';
import { LayoutDashboard, Send, Zap, User, LogOut } from 'lucide-react';

const Sidebar = ({ activePage, setActivePage }) => {
    const menuItems = [
        { id: 'dashboard', icon: LayoutDashboard, label: 'Overview' },
        { id: 'transact', icon: Send, label: 'Transact' },
        { id: 'vas', icon: Zap, label: 'Services' }, // Buy Airtime/Elec
        { id: 'profile', icon: User, label: 'My Profile' },
    ];

    return (
        <div className="h-screen w-64 bg-white border-r border-gray-200 flex flex-col fixed left-0 top-0">
            {/* Logo Area */}
            <div className="p-6 flex items-center gap-3 border-b border-gray-100">
                <div className="w-8 h-8 bg-brand-primary rounded-lg flex items-center justify-center text-white font-bold text-xl">
                    Z
                </div>
                <span className="font-bold text-xl text-brand-primary">ZuluFlow</span>
            </div>

            {/* Navigation Items */}
            <nav className="flex-1 p-4 space-y-2">
                {menuItems.map((item) => (
                    <button
                        key={item.id}
                        onClick={() => setActivePage(item.id)}
                        className={`w-full flex items-center gap-3 px-4 py-3 rounded-xl transition-all duration-200 ${
                            activePage === item.id
                                ? 'bg-brand-primary text-white shadow-lg shadow-brand-primary/30'
                                : 'text-gray-500 hover:bg-gray-50 hover:text-brand-primary'
                        }`}
                    >
                        <item.icon size={20} />
                        <span className="font-medium">{item.label}</span>
                    </button>
                ))}
            </nav>

            {/* Logout Button */}
            <div className="p-4 border-t border-gray-100">
                <button className="w-full flex items-center gap-3 px-4 py-3 text-red-500 hover:bg-red-50 rounded-xl transition-colors">
                    <LogOut size={20} />
                    <span className="font-medium">Sign Out</span>
                </button>
            </div>
        </div>
    );
};

export default Sidebar;