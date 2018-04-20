-- phpMyAdmin SQL Dump
-- version 4.7.9
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le :  ven. 20 avr. 2018 à 15:09
-- Version du serveur :  10.1.31-MariaDB
-- Version de PHP :  7.2.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `proxibanquesislmalc`
--

CREATE DATABASE `proxibanquesislmalc`;

-- --------------------------------------------------------

--
-- Structure de la table `cartebancaire`
--

CREATE TABLE `cartebancaire` (
  `numeroCarte` bigint(20) NOT NULL,
  `type` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE `client` (
  `idClient` bigint(20) NOT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `codePostal` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `telephone` varchar(255) DEFAULT NULL,
  `ville` varchar(255) DEFAULT NULL,
  `compte_courant_id` bigint(20) DEFAULT NULL,
  `compte_epargne_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `compte`
--

CREATE TABLE `compte` (
  `numeroCompte` bigint(20) NOT NULL,
  `dateOuverture` varchar(255) DEFAULT NULL,
  `solde` double NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `comptecourant`
--

CREATE TABLE `comptecourant` (
  `numeroCompte` bigint(20) NOT NULL,
  `numero_carte` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `compteepargne`
--

CREATE TABLE `compteepargne` (
  `tauxRemun` double NOT NULL,
  `numeroCompte` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Structure de la table `conseiller`
--

CREATE TABLE `conseiller` (
  `idConseiller` bigint(20) NOT NULL,
  `login` varchar(255) DEFAULT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Déchargement des données de la table `conseiller`
--

INSERT INTO `conseiller` (`idConseiller`, `login`, `nom`, `password`, `prenom`) VALUES
(1, 'jdupont', 'Dupont', '1234', 'Jacques');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `cartebancaire`
--
ALTER TABLE `cartebancaire`
  ADD PRIMARY KEY (`numeroCarte`);

--
-- Index pour la table `client`
--
ALTER TABLE `client`
  ADD PRIMARY KEY (`idClient`),
  ADD UNIQUE KEY `UK_g642gni73h9kjft569cw9mnu0` (`compte_courant_id`),
  ADD UNIQUE KEY `UK_d1wyqlf07lcnv0s8s5q63qbac` (`compte_epargne_id`);

--
-- Index pour la table `compte`
--
ALTER TABLE `compte`
  ADD PRIMARY KEY (`numeroCompte`);

--
-- Index pour la table `comptecourant`
--
ALTER TABLE `comptecourant`
  ADD PRIMARY KEY (`numeroCompte`),
  ADD UNIQUE KEY `UK_lonnvh3a0vm4f5qa40w4ml7e3` (`numero_carte`);

--
-- Index pour la table `compteepargne`
--
ALTER TABLE `compteepargne`
  ADD PRIMARY KEY (`numeroCompte`);

--
-- Index pour la table `conseiller`
--
ALTER TABLE `conseiller`
  ADD PRIMARY KEY (`idConseiller`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `client`
--
ALTER TABLE `client`
  MODIFY `idClient` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT pour la table `conseiller`
--
ALTER TABLE `conseiller`
  MODIFY `idConseiller` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `client`
--
ALTER TABLE `client`
  ADD CONSTRAINT `FK_d1wyqlf07lcnv0s8s5q63qbac` FOREIGN KEY (`compte_epargne_id`) REFERENCES `compteepargne` (`numeroCompte`),
  ADD CONSTRAINT `FK_g642gni73h9kjft569cw9mnu0` FOREIGN KEY (`compte_courant_id`) REFERENCES `comptecourant` (`numeroCompte`);

--
-- Contraintes pour la table `comptecourant`
--
ALTER TABLE `comptecourant`
  ADD CONSTRAINT `FK_cpaijb3a9ruo5alree6ntsr7l` FOREIGN KEY (`numeroCompte`) REFERENCES `compte` (`numeroCompte`),
  ADD CONSTRAINT `FK_lonnvh3a0vm4f5qa40w4ml7e3` FOREIGN KEY (`numero_carte`) REFERENCES `cartebancaire` (`numeroCarte`);

--
-- Contraintes pour la table `compteepargne`
--
ALTER TABLE `compteepargne`
  ADD CONSTRAINT `FK_mrdvy9yp304d7s7i4982x4euy` FOREIGN KEY (`numeroCompte`) REFERENCES `compte` (`numeroCompte`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
