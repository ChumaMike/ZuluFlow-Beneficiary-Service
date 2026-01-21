/** @type {import('tailwindcss').Config} */
export default {
    content: [
        "./index.html",
        "./src/**/*.{js,ts,jsx,tsx}",
    ],
    theme: {
        extend: {
            colors: {
                // We'll define our "Brand Colors" here
                brand: {
                    primary: '#004d40', // Deep Jungle Green (Like a serious bank)
                    secondary: '#00796b',
                    accent: '#ffca28',  // Gold/Yellow (For buttons)
                    light: '#f4f4f9',   // Background grey
                }
            }
        },
    },
    plugins: [],
}