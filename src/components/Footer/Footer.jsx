import React from 'react';
import './Footer.css';

const Footer = () => {
return (
<footer className="footer">
<div className="footer-container">
{/* Quick Links Section */}
<div className="footer-column">
<h2 className="footer-heading">Quick Links</h2>
<ul>
<li><a href="#home">Home</a></li>
<li><a href="#Bookings">Booking</a></li>
<li><a href="#Aboutus">AboutUs</a></li>
<li><a href="#FAQ">FAQ</a></li>
</ul>
</div>

{/* Legal Links Section */}
<div className="footer-column">
<h2 className="footer-heading">Legal</h2>
<ul>
<li><a href="#terms">Terms of Service</a></li>
<li><a href="#privacy">Privacy Policy</a></li>
<li><a href="#refund">Refund Policy</a></li>
</ul>
</div>

{/* Contact Us Section */}
<div className="footer-column">
<h2 className="footer-heading">Contact Us</h2>
<ul>
<li><i className="fas fa-phone"></i> Phone: (123) 456-7890</li>
<li><i className="fas fa-phone-square-alt"></i> Landline: +1 (987) 654-3210</li>
<li><i className="fas fa-envelope"></i> Email: support@wheelwise.com</li>
</ul>
</div>

{/* Follow Us Section */}
<div className="footer-column">
<h2 className="footer-heading">Follow Us</h2>
<div className="social-icons">
<a href="https://www.facebook.com" target="_blank" rel="noopener noreferrer" className="social-item">
<i className="fab fa-facebook"></i>
<span>Facebook</span>
</a>
<a href="https://www.twitter.com" target="_blank" rel="noopener noreferrer" className="social-item">
<i className="fab fa-twitter"></i>
<span>Twitter</span>
</a>
<a href="https://www.instagram.com" target="_blank" rel="noopener noreferrer" className="social-item">
<i className="fab fa-instagram"></i>
<span>Instagram</span>
</a>
<a href="https://www.linkedin.com" target="_blank" rel="noopener noreferrer" className="social-item">
<i className="fab fa-linkedin"></i>
<span>LinkedIn</span>
</a>
</div>
</div>
</div>

{/* Footer Bottom */}
<div className="footer-bottom">
<p className="moto">Wheelwise - "Your Destination for Smart Car Rentals"</p>
<p className="copyright">&copy; 2024 Wheelwise. All Rights Reserved.</p>
</div>
</footer>
);
};

export default Footer;