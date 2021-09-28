module.exports = {
  purge: ["./pages/**/*.{js,ts,jsx,tsx}", "./components/**/*.{js,ts,jsx,tsx}"],
  darkMode: false, // or 'media' or 'class'
  theme: {
    extend: {
      colors: {
        codeChef: {
          DEFAULT: "#666666",
          blue: "#3b5998",
          btn: "#1D1F21",
          btnFocus: "#B1DAFA",
          bgOutPut: "#F8F9FA",
        },
      },
      height: {
        editor: "748px",
        editorContainer: "962px",
      },
    },
  },
  variants: {
    extend: {},
  },
  plugins: [require("@tailwindcss/forms")],
};
