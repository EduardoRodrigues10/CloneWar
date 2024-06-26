/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    "./src/**/*.{js,jsx,ts,tsx}",
  ],
  theme: {
    extend: {
      colors: {
        "light-pink": "#081A51",
        "light-white": "rgba(255,255,255,0.17)",
        "red": "#FF6962"
      },
    },
  },
  plugins: [],
}
