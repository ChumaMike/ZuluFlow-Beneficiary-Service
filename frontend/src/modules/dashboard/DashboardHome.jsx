import React from 'react';
import { TrendingUp, TrendingDown, CreditCard } from 'lucide-react';

const DashboardHome = () => {
    return (
        <div className="space-y-6">
            <header>
                <h1 className="text-2xl font-bold text-gray-800">Good Morning, Chief! ☀️</h1>
                <p className="text-gray-500">Here is your financial overview.</p>
            </header>

            {/* Account Cards - The "FNB Carousel" Vibe */}
            <div className="grid grid-cols-1 md:grid-cols-2 gap-6">
                {/* Card 1: Cheque */}
                <div className="bg-brand-primary text-white p-6 rounded-2xl shadow-xl shadow-brand-primary/20 relative overflow-hidden">
                    <div className="absolute right-[-20px] top-[-20px] opacity-10">
                        <CreditCard size={150} />
                    </div>
                    <div className="relative z-10">
                        <p className="text-brand-accent font-medium mb-1">Cheque Account</p>
                        <h2 className="text-3xl font-bold mb-4">R 24,500.00</h2>
                        <div className="flex justify-between items-end">
                            <p className="text-sm opacity-80">**** **** **** 4226</p>
                            <span className="bg-white/20 px-3 py-1 rounded-full text-xs font-bold">ACTIVE</span>
                        </div>
                    </div>
                </div>

                {/* Card 2: Savings */}
                <div className="bg-white p-6 rounded-2xl border border-gray-200 shadow-sm hover:shadow-md transition-shadow">
                    <p className="text-gray-500 font-medium mb-1">Savings Pocket</p>
                    <h2 className="text-3xl font-bold text-gray-800 mb-4">R 5,000.00</h2>
                    <div className="flex justify-between items-end">
                        <p className="text-sm text-gray-400">**** **** **** 9001</p>
                        <span className="bg-green-100 text-green-700 px-3 py-1 rounded-full text-xs font-bold">GROWING</span>
                    </div>
                </div>
            </div>

            {/* Recent Transactions Placeholder */}
            <div className="bg-white rounded-2xl border border-gray-200 p-6">
                <div className="flex justify-between items-center mb-4">
                    <h3 className="font-bold text-lg text-gray-800">Recent Transactions</h3>
                    <button className="text-brand-primary text-sm font-medium hover:underline">View All</button>
                </div>

                <div className="space-y-4">
                    <div className="flex items-center justify-between p-3 hover:bg-gray-50 rounded-lg transition-colors">
                        <div className="flex items-center gap-4">
                            <div className="bg-red-100 text-red-600 p-2 rounded-full">
                                <TrendingDown size={20} />
                            </div>
                            <div>
                                <p className="font-bold text-gray-800">Woolworths Food</p>
                                <p className="text-xs text-gray-500">21 Jan, 14:30</p>
                            </div>
                        </div>
                        <span className="font-bold text-red-600">- R 450.00</span>
                    </div>

                    <div className="flex items-center justify-between p-3 hover:bg-gray-50 rounded-lg transition-colors">
                        <div className="flex items-center gap-4">
                            <div className="bg-green-100 text-green-600 p-2 rounded-full">
                                <TrendingUp size={20} />
                            </div>
                            <div>
                                <p className="font-bold text-gray-800">Salary: Tech Corp</p>
                                <p className="text-xs text-gray-500">25 Jan, 08:00</p>
                            </div>
                        </div>
                        <span className="font-bold text-green-600">+ R 45,000.00</span>
                    </div>
                </div>
            </div>
        </div>
    );
};

export default DashboardHome;