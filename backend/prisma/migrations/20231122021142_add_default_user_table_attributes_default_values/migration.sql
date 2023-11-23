-- RedefineTables
PRAGMA foreign_keys=OFF;
CREATE TABLE "new_User" (
    "id" INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT,
    "username" TEXT NOT NULL,
    "name" TEXT NOT NULL DEFAULT '',
    "email" TEXT NOT NULL,
    "password" TEXT NOT NULL,
    "imgSrc" TEXT NOT NULL DEFAULT '',
    "dayStreak" INTEGER NOT NULL DEFAULT 0,
    "cardsReviewed" INTEGER NOT NULL DEFAULT 0
);
INSERT INTO "new_User" ("cardsReviewed", "dayStreak", "email", "id", "imgSrc", "name", "password", "username") SELECT "cardsReviewed", "dayStreak", "email", "id", "imgSrc", "name", "password", "username" FROM "User";
DROP TABLE "User";
ALTER TABLE "new_User" RENAME TO "User";
CREATE UNIQUE INDEX "User_username_key" ON "User"("username");
CREATE UNIQUE INDEX "User_email_key" ON "User"("email");
PRAGMA foreign_key_check;
PRAGMA foreign_keys=ON;
