# Purpose

An online order placing for restaurant.

## Requirements

The system has 4 distinct entities and 1 relationship entity.

User/Customer : the one who uses the system or website to order food, view the products or menu and also post review(s).

Admin: the one who manages the users, creates products and track other activities in the system.

Product: the main focus of the system. The user sees the products and can add them to his/her order(s).

Review: a customer or user who post reviews about the website or interface, products and more to be added in the future.

Order: this contains the products selected by a user. Just like a shopping cart.



## Getting started

```bash
# Clone repository
git clone https://github.com/Amirneutron/Restaurant-Project.git

# Change into the directory

## Setup backend
cd server && npm install
npm run dev

## Setup frontend
cd client && npm install
npm run serve
```

## Visual Studio Code (VSCode)

Open the `server` and `client` in separate VSCode workspaces or open the combined [backend-frontend.code-workspace](./backend-frontend.code-workspace). Otherwise, workspace-specific settings don't work properly.

