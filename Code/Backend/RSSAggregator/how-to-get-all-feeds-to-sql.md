## Example of full installation on Linux Debian/Ubuntu
```sh
# If you use an Apache Web server (otherwise you need another Web server)
sudo apt-get install apache2
sudo a2enmod headers expires rewrite ssl
# (Optional) If you want a MySQL database server
sudo apt-get install mysql-server mysql-client php5-mysql
# Main components (git is optional if you manually download the installation files)
sudo apt-get install git php5 php5-curl php5-gmp php5-intl php5-json php5-sqlite
# Restart Web server
sudo service apache2 restart

# For FreshRSS itself
cd /usr/share/
sudo git clone https://github.com/FreshRSS/FreshRSS.git
# Set the rights so that your Web browser can access the files
cd FreshRSS
sudo chown -R :www-data .
sudo chmod -R g+w ./data/
# Publish FreshRSS in your public HTML directory
sudo ln -s /usr/share/FreshRSS/p /var/www/html/FreshRSS

# Update to a newer version of FreshRSS
cd /usr/share/FreshRSS
sudo git reset --hard
sudo git pull
sudo chown -R :www-data .
sudo chmod -R g+w ./data/

# Navigate to http://example.net/FreshRSS to complete the installation.
# (Please use no authentication), when you are done then import the feedlist from our repository
# Open the administrator interface and import our feed list and remove all others.


# now remove the content from web
rm -rf /var/www/html/FreshRSS (you are only deleting the symlink)
# In this way no one from external can access it anymore.

# Check if the cronjob works. to do so execute in your shell
php /your-path/FreshRSS/app/actualize_script.php

# Give yourself permission again.... (only needed if you dont start the conjob as www-data)
chown -R myuser:myuser /usr/share/FreshRSS 


# You should see an output like:
snapo@predictor:~$ php /home/snapo/Code/Backend/FreshRSS/app/actualize_script.php
Starting feed actualization at 2016-08-16T20:40:02+00:00
Actualize predictor...
Done.
Ending feed actualization at 2016-08-16T20:40:02+00:00
Feed actualizations took 0 day(s), 0 hour(s),  0 minute(s) and 0 seconds for 1 users
Results:
predictor OK
End.
snapo@predictor:~$

```