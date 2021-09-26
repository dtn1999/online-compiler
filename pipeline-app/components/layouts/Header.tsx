import React from "react";
import { AppBar, Toolbar, IconButton, Typography, Button } from "@mui/material";
import Image from "next/image";
import { makeStyles } from "@mui/styles";
import Link from "next/link";

const useStyles = makeStyles({
  appBar: {
    backgroundColor: "#fff",
    display: "flex",
    flexDirection: "row",
    justifyContent: "space-around",
    color: "#000",
  },
  button: {
    backgroundColor: "#498D44",
    color: "#fff",
    height: 48,
  },
  toolbar: {},
  link: {
    marginLeft: 10,
    marginRight: 10,
  },
  image: {
    marginRight: 100,
  },
});

const links: string[] = [
  "Product",
  "Pricing",
  "Developers",
  "Resources",
  "Support",
  "Company",
];

const Header: React.FC = ({ children }) => {
  const classes = useStyles();

  return (
    <AppBar position="static" className={classes.appBar}>
      <Toolbar className={classes.toolbar}>
        <Image
          src="/logoName.svg"
          alt="circle ci logo"
          width={100}
          height={56}
        />

        {links.map((link) => (
          <a href="#" key={link} className={classes.link}>
            {link}
          </a>
        ))}

        <Button variant="contained" color="success" className={classes.button}>
          Go to Application
        </Button>
      </Toolbar>
    </AppBar>
  );
};

export default Header;
