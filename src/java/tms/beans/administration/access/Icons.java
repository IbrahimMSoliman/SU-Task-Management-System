package tms.beans.administration.access;

import java.io.Serializable;
import java.sql.ResultSet;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

import jakarta.annotation.PostConstruct;

import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;

import tms.beans.common.msgr;
import tms.common.common_functions;
import tms.db.connect;

@Named("icons")
@SessionScoped
public class Icons implements Serializable
    {
    private List<icons_class> icons_list = new ArrayList<>();
    private String item_id;
    private String item_title;
    private String item_url;
    private String item_icon;

    @PostConstruct
    public void init()
        {
        icons_list.add(new icons_class("fa fa-w fa-bed"));
        icons_list.add(new icons_class("fa fa-w fa-buysellads"));
        icons_list.add(new icons_class("fa fa-w fa-cart-arrow-down"));
        icons_list.add(new icons_class("fa fa-w fa-cart-plus"));
        icons_list.add(new icons_class("fa fa-w fa-connectdevelop"));
        icons_list.add(new icons_class("fa fa-w fa-dashcube"));
        icons_list.add(new icons_class("fa fa-w fa-diamond"));
        icons_list.add(new icons_class("fa fa-w fa-facebook-official"));
        icons_list.add(new icons_class("fa fa-w fa-forumbee"));
        icons_list.add(new icons_class("fa fa-w fa-heartbeat"));
        icons_list.add(new icons_class("fa fa-w fa-hotel (alias)"));
        icons_list.add(new icons_class("fa fa-w fa-leanpub"));
        icons_list.add(new icons_class("fa fa-w fa-mars"));
        icons_list.add(new icons_class("fa fa-w fa-mars-double"));
        icons_list.add(new icons_class("fa fa-w fa-mars-stroke"));
        icons_list.add(new icons_class("fa fa-w fa-mars-stroke-h"));
        icons_list.add(new icons_class("fa fa-w fa-mars-stroke-v"));
        icons_list.add(new icons_class("fa fa-w fa-medium"));
        icons_list.add(new icons_class("fa fa-w fa-mercury"));
        icons_list.add(new icons_class("fa fa-w fa-motorcycle"));
        icons_list.add(new icons_class("fa fa-w fa-neuter"));
        icons_list.add(new icons_class("fa fa-w fa-pinterest-p"));
        icons_list.add(new icons_class("fa fa-w fa-sellsy"));
        icons_list.add(new icons_class("fa fa-w fa-server"));
        icons_list.add(new icons_class("fa fa-w fa-ship"));
        icons_list.add(new icons_class("fa fa-w fa-shirtsinbulk"));
        icons_list.add(new icons_class("fa fa-w fa-simplybuilt"));
        icons_list.add(new icons_class("fa fa-w fa-skyatlas"));
        icons_list.add(new icons_class("fa fa-w fa-street-view"));
        icons_list.add(new icons_class("fa fa-w fa-subway"));
        icons_list.add(new icons_class("fa fa-w fa-train"));
        icons_list.add(new icons_class("fa fa-w fa-transgender"));
        icons_list.add(new icons_class("fa fa-w fa-transgender-alt"));
        icons_list.add(new icons_class("fa fa-w fa-user-plus"));
        icons_list.add(new icons_class("fa fa-w fa-user-secret"));
        icons_list.add(new icons_class("fa fa-w fa-user-times"));
        icons_list.add(new icons_class("fa fa-w fa-venus"));
        icons_list.add(new icons_class("fa fa-w fa-venus-double"));
        icons_list.add(new icons_class("fa fa-w fa-venus-mars"));
        icons_list.add(new icons_class("fa fa-w fa-viacoin"));
        icons_list.add(new icons_class("fa fa-w fa-whatsapp"));
        icons_list.add(new icons_class("fa fa-w fa-adjust"));
        icons_list.add(new icons_class("fa fa-w fa-adn"));
        icons_list.add(new icons_class("fa fa-w fa-align-center"));
        icons_list.add(new icons_class("fa fa-w fa-align-justify"));
        icons_list.add(new icons_class("fa fa-w fa-align-left"));
        icons_list.add(new icons_class("fa fa-w fa-align-right"));
        icons_list.add(new icons_class("fa fa-w fa-ambulance"));
        icons_list.add(new icons_class("fa fa-w fa-anchor"));
        icons_list.add(new icons_class("fa fa-w fa-android"));
        icons_list.add(new icons_class("fa fa-w fa-angellist"));
        icons_list.add(new icons_class("fa fa-w fa-angle-double-down"));
        icons_list.add(new icons_class("fa fa-w fa-angle-double-left"));
        icons_list.add(new icons_class("fa fa-w fa-angle-double-right"));
        icons_list.add(new icons_class("fa fa-w fa-angle-double-up"));
        icons_list.add(new icons_class("fa fa-w fa-angle-down"));
        icons_list.add(new icons_class("fa fa-w fa-angle-left"));
        icons_list.add(new icons_class("fa fa-w fa-angle-right"));
        icons_list.add(new icons_class("fa fa-w fa-angle-up"));
        icons_list.add(new icons_class("fa fa-w fa-apple"));
        icons_list.add(new icons_class("fa fa-w fa-archive"));
        icons_list.add(new icons_class("fa fa-w fa-area-chart"));
        icons_list.add(new icons_class("fa fa-w fa-arrow-circle-down"));
        icons_list.add(new icons_class("fa fa-w fa-arrow-circle-left"));
        icons_list.add(new icons_class("fa fa-w fa-arrow-circle-o-down"));
        icons_list.add(new icons_class("fa fa-w fa-arrow-circle-o-left"));
        icons_list.add(new icons_class("fa fa-w fa-arrow-circle-o-right"));
        icons_list.add(new icons_class("fa fa-w fa-arrow-circle-o-up"));
        icons_list.add(new icons_class("fa fa-w fa-arrow-circle-right"));
        icons_list.add(new icons_class("fa fa-w fa-arrow-circle-up"));
        icons_list.add(new icons_class("fa fa-w fa-arrow-down"));
        icons_list.add(new icons_class("fa fa-w fa-arrow-left"));
        icons_list.add(new icons_class("fa fa-w fa-arrow-right"));
        icons_list.add(new icons_class("fa fa-w fa-arrow-up"));
        icons_list.add(new icons_class("fa fa-w fa-arrows"));
        icons_list.add(new icons_class("fa fa-w fa-arrows-alt"));
        icons_list.add(new icons_class("fa fa-w fa-arrows-h"));
        icons_list.add(new icons_class("fa fa-w fa-arrows-v"));
        icons_list.add(new icons_class("fa fa-w fa-asterisk"));
        icons_list.add(new icons_class("fa fa-w fa-at"));
        icons_list.add(new icons_class("fa fa-w fa-automobile"));
        icons_list.add(new icons_class("fa fa-w fa-backward"));
        icons_list.add(new icons_class("fa fa-w fa-ban"));
        icons_list.add(new icons_class("fa fa-w fa-bank"));
        icons_list.add(new icons_class("fa fa-w fa-bar-chart"));
        icons_list.add(new icons_class("fa fa-w fa-bar-chart-o"));
        icons_list.add(new icons_class("fa fa-w fa-barcode"));
        icons_list.add(new icons_class("fa fa-w fa-bars"));
        icons_list.add(new icons_class("fa fa-w fa-beer"));
        icons_list.add(new icons_class("fa fa-w fa-behance"));
        icons_list.add(new icons_class("fa fa-w fa-behance-square"));
        icons_list.add(new icons_class("fa fa-w fa-bell"));
        icons_list.add(new icons_class("fa fa-w fa-bell-o"));
        icons_list.add(new icons_class("fa fa-w fa-bell-slash"));
        icons_list.add(new icons_class("fa fa-w fa-bell-slash-o"));
        icons_list.add(new icons_class("fa fa-w fa-bicycle"));
        icons_list.add(new icons_class("fa fa-w fa-binoculars"));
        icons_list.add(new icons_class("fa fa-w fa-birthday-cake"));
        icons_list.add(new icons_class("fa fa-w fa-bitbucket"));
        icons_list.add(new icons_class("fa fa-w fa-bitbucket-square"));
        icons_list.add(new icons_class("fa fa-w fa-bitcoin"));
        icons_list.add(new icons_class("fa fa-w fa-bold"));
        icons_list.add(new icons_class("fa fa-w fa-bolt"));
        icons_list.add(new icons_class("fa fa-w fa-bomb"));
        icons_list.add(new icons_class("fa fa-w fa-book"));
        icons_list.add(new icons_class("fa fa-w fa-bookmark"));
        icons_list.add(new icons_class("fa fa-w fa-bookmark-o"));
        icons_list.add(new icons_class("fa fa-w fa-briefcase"));
        icons_list.add(new icons_class("fa fa-w fa-btc"));
        icons_list.add(new icons_class("fa fa-w fa-bug"));
        icons_list.add(new icons_class("fa fa-w fa-building"));
        icons_list.add(new icons_class("fa fa-w fa-building-o"));
        icons_list.add(new icons_class("fa fa-w fa-bullhorn"));
        icons_list.add(new icons_class("fa fa-w fa-bullseye"));
        icons_list.add(new icons_class("fa fa-w fa-bus"));
        icons_list.add(new icons_class("fa fa-w fa-cab"));
        icons_list.add(new icons_class("fa fa-w fa-calculator"));
        icons_list.add(new icons_class("fa fa-w fa-calendar"));
        icons_list.add(new icons_class("fa fa-w fa-calendar-o"));
        icons_list.add(new icons_class("fa fa-w fa-camera"));
        icons_list.add(new icons_class("fa fa-w fa-camera-retro"));
        icons_list.add(new icons_class("fa fa-w fa-car"));
        icons_list.add(new icons_class("fa fa-w fa-caret-down"));
        icons_list.add(new icons_class("fa fa-w fa-caret-left"));
        icons_list.add(new icons_class("fa fa-w fa-caret-right"));
        icons_list.add(new icons_class("fa fa-w fa-caret-square-o-down"));
        icons_list.add(new icons_class("fa fa-w fa-caret-square-o-left"));
        icons_list.add(new icons_class("fa fa-w fa-caret-square-o-right"));
        icons_list.add(new icons_class("fa fa-w fa-caret-square-o-up"));
        icons_list.add(new icons_class("fa fa-w fa-caret-up"));
        icons_list.add(new icons_class("fa fa-w fa-cc"));
        icons_list.add(new icons_class("fa fa-w fa-cc-amex"));
        icons_list.add(new icons_class("fa fa-w fa-cc-discover"));
        icons_list.add(new icons_class("fa fa-w fa-cc-mastercard"));
        icons_list.add(new icons_class("fa fa-w fa-cc-paypal"));
        icons_list.add(new icons_class("fa fa-w fa-cc-stripe"));
        icons_list.add(new icons_class("fa fa-w fa-cc-visa"));
        icons_list.add(new icons_class("fa fa-w fa-certificate"));
        icons_list.add(new icons_class("fa fa-w fa-chain"));
        icons_list.add(new icons_class("fa fa-w fa-chain-broken"));
        icons_list.add(new icons_class("fa fa-w fa-check"));
        icons_list.add(new icons_class("fa fa-w fa-check-circle"));
        icons_list.add(new icons_class("fa fa-w fa-check-circle-o"));
        icons_list.add(new icons_class("fa fa-w fa-check-square"));
        icons_list.add(new icons_class("fa fa-w fa-check-square-o"));
        icons_list.add(new icons_class("fa fa-w fa-chevron-circle-down"));
        icons_list.add(new icons_class("fa fa-w fa-chevron-circle-left"));
        icons_list.add(new icons_class("fa fa-w fa-chevron-circle-right"));
        icons_list.add(new icons_class("fa fa-w fa-chevron-circle-up"));
        icons_list.add(new icons_class("fa fa-w fa-chevron-down"));
        icons_list.add(new icons_class("fa fa-w fa-chevron-left"));
        icons_list.add(new icons_class("fa fa-w fa-chevron-right"));
        icons_list.add(new icons_class("fa fa-w fa-chevron-up"));
        icons_list.add(new icons_class("fa fa-w fa-child"));
        icons_list.add(new icons_class("fa fa-w fa-circle"));
        icons_list.add(new icons_class("fa fa-w fa-circle-o"));
        icons_list.add(new icons_class("fa fa-w fa-circle-o-notch"));
        icons_list.add(new icons_class("fa fa-w fa-circle-thin"));
        icons_list.add(new icons_class("fa fa-w fa-clipboard"));
        icons_list.add(new icons_class("fa fa-w fa-clock-o"));
        icons_list.add(new icons_class("fa fa-w fa-close"));
        icons_list.add(new icons_class("fa fa-w fa-cloud"));
        icons_list.add(new icons_class("fa fa-w fa-cloud-download"));
        icons_list.add(new icons_class("fa fa-w fa-cloud-upload"));
        icons_list.add(new icons_class("fa fa-w fa-cny"));
        icons_list.add(new icons_class("fa fa-w fa-code"));
        icons_list.add(new icons_class("fa fa-w fa-code-fork"));
        icons_list.add(new icons_class("fa fa-w fa-codepen"));
        icons_list.add(new icons_class("fa fa-w fa-coffee"));
        icons_list.add(new icons_class("fa fa-w fa-cog"));
        icons_list.add(new icons_class("fa fa-w fa-cogs"));
        icons_list.add(new icons_class("fa fa-w fa-columns"));
        icons_list.add(new icons_class("fa fa-w fa-comment"));
        icons_list.add(new icons_class("fa fa-w fa-comment-o"));
        icons_list.add(new icons_class("fa fa-w fa-comments"));
        icons_list.add(new icons_class("fa fa-w fa-comments-o"));
        icons_list.add(new icons_class("fa fa-w fa-compass"));
        icons_list.add(new icons_class("fa fa-w fa-compress"));
        icons_list.add(new icons_class("fa fa-w fa-copy"));
        icons_list.add(new icons_class("fa fa-w fa-copyright"));
        icons_list.add(new icons_class("fa fa-w fa-credit-card"));
        icons_list.add(new icons_class("fa fa-w fa-crop"));
        icons_list.add(new icons_class("fa fa-w fa-crosshairs"));
        icons_list.add(new icons_class("fa fa-w fa-css3"));
        icons_list.add(new icons_class("fa fa-w fa-cube"));
        icons_list.add(new icons_class("fa fa-w fa-cubes"));
        icons_list.add(new icons_class("fa fa-w fa-cut"));
        icons_list.add(new icons_class("fa fa-w fa-cutlery"));
        icons_list.add(new icons_class("fa fa-w fa-dashboard"));
        icons_list.add(new icons_class("fa fa-w fa-database"));
        icons_list.add(new icons_class("fa fa-w fa-dedent"));
        icons_list.add(new icons_class("fa fa-w fa-delicious"));
        icons_list.add(new icons_class("fa fa-w fa-desktop"));
        icons_list.add(new icons_class("fa fa-w fa-deviantart"));
        icons_list.add(new icons_class("fa fa-w fa-digg"));
        icons_list.add(new icons_class("fa fa-w fa-dollar"));
        icons_list.add(new icons_class("fa fa-w fa-dot-circle-o"));
        icons_list.add(new icons_class("fa fa-w fa-download"));
        icons_list.add(new icons_class("fa fa-w fa-dribbble"));
        icons_list.add(new icons_class("fa fa-w fa-dropbox"));
        icons_list.add(new icons_class("fa fa-w fa-drupal"));
        icons_list.add(new icons_class("fa fa-w fa-edit"));
        icons_list.add(new icons_class("fa fa-w fa-eject"));
        icons_list.add(new icons_class("fa fa-w fa-ellipsis-h"));
        icons_list.add(new icons_class("fa fa-w fa-ellipsis-v"));
        icons_list.add(new icons_class("fa fa-w fa-empire"));
        icons_list.add(new icons_class("fa fa-w fa-envelope"));
        icons_list.add(new icons_class("fa fa-w fa-envelope-o"));
        icons_list.add(new icons_class("fa fa-w fa-envelope-square"));
        icons_list.add(new icons_class("fa fa-w fa-eraser"));
        icons_list.add(new icons_class("fa fa-w fa-eur"));
        icons_list.add(new icons_class("fa fa-w fa-euro"));
        icons_list.add(new icons_class("fa fa-w fa-exchange"));
        icons_list.add(new icons_class("fa fa-w fa-exclamation"));
        icons_list.add(new icons_class("fa fa-w fa-exclamation-circle"));
        icons_list.add(new icons_class("fa fa-w fa-exclamation-triangle"));
        icons_list.add(new icons_class("fa fa-w fa-expand"));
        icons_list.add(new icons_class("fa fa-w fa-external-link"));
        icons_list.add(new icons_class("fa fa-w fa-external-link-square"));
        icons_list.add(new icons_class("fa fa-w fa-eye"));
        icons_list.add(new icons_class("fa fa-w fa-eye-slash"));
        icons_list.add(new icons_class("fa fa-w fa-eyedropper"));
        icons_list.add(new icons_class("fa fa-w fa-facebook"));
        icons_list.add(new icons_class("fa fa-w fa-facebook-square"));
        icons_list.add(new icons_class("fa fa-w fa-fast-backward"));
        icons_list.add(new icons_class("fa fa-w fa-fast-forward"));
        icons_list.add(new icons_class("fa fa-w fa-fax"));
        icons_list.add(new icons_class("fa fa-w fa-female"));
        icons_list.add(new icons_class("fa fa-w fa-fighter-jet"));
        icons_list.add(new icons_class("fa fa-w fa-file"));
        icons_list.add(new icons_class("fa fa-w fa-file-archive-o"));
        icons_list.add(new icons_class("fa fa-w fa-file-audio-o"));
        icons_list.add(new icons_class("fa fa-w fa-file-code-o"));
        icons_list.add(new icons_class("fa fa-w fa-file-excel-o"));
        icons_list.add(new icons_class("fa fa-w fa-file-image-o"));
        icons_list.add(new icons_class("fa fa-w fa-file-movie-o"));
        icons_list.add(new icons_class("fa fa-w fa-file-o"));
        icons_list.add(new icons_class("fa fa-w fa-file-pdf-o"));
        icons_list.add(new icons_class("fa fa-w fa-file-photo-o"));
        icons_list.add(new icons_class("fa fa-w fa-file-picture-o"));
        icons_list.add(new icons_class("fa fa-w fa-file-powerpoint-o"));
        icons_list.add(new icons_class("fa fa-w fa-file-sound-o"));
        icons_list.add(new icons_class("fa fa-w fa-file-text"));
        icons_list.add(new icons_class("fa fa-w fa-file-text-o"));
        icons_list.add(new icons_class("fa fa-w fa-file-video-o"));
        icons_list.add(new icons_class("fa fa-w fa-file-word-o"));
        icons_list.add(new icons_class("fa fa-w fa-file-zip-o"));
        icons_list.add(new icons_class("fa fa-w fa-files-o"));
        icons_list.add(new icons_class("fa fa-w fa-film"));
        icons_list.add(new icons_class("fa fa-w fa-filter"));
        icons_list.add(new icons_class("fa fa-w fa-fire"));
        icons_list.add(new icons_class("fa fa-w fa-fire-extinguisher"));
        icons_list.add(new icons_class("fa fa-w fa-flag"));
        icons_list.add(new icons_class("fa fa-w fa-flag-checkered"));
        icons_list.add(new icons_class("fa fa-w fa-flag-o"));
        icons_list.add(new icons_class("fa fa-w fa-flash"));
        icons_list.add(new icons_class("fa fa-w fa-flask"));
        icons_list.add(new icons_class("fa fa-w fa-flickr"));
        icons_list.add(new icons_class("fa fa-w fa-floppy-o"));
        icons_list.add(new icons_class("fa fa-w fa-folder"));
        icons_list.add(new icons_class("fa fa-w fa-folder-o"));
        icons_list.add(new icons_class("fa fa-w fa-folder-open"));
        icons_list.add(new icons_class("fa fa-w fa-folder-open-o"));
        icons_list.add(new icons_class("fa fa-w fa-font"));
        icons_list.add(new icons_class("fa fa-w fa-forward"));
        icons_list.add(new icons_class("fa fa-w fa-foursquare"));
        icons_list.add(new icons_class("fa fa-w fa-frown-o"));
        icons_list.add(new icons_class("fa fa-w fa-futbol-o"));
        icons_list.add(new icons_class("fa fa-w fa-gamepad"));
        icons_list.add(new icons_class("fa fa-w fa-gavel"));
        icons_list.add(new icons_class("fa fa-w fa-gbp"));
        icons_list.add(new icons_class("fa fa-w fa-ge"));
        icons_list.add(new icons_class("fa fa-w fa-gear"));
        icons_list.add(new icons_class("fa fa-w fa-gears"));
        icons_list.add(new icons_class("fa fa-w fa-gift"));
        icons_list.add(new icons_class("fa fa-w fa-git"));
        icons_list.add(new icons_class("fa fa-w fa-git-square"));
        icons_list.add(new icons_class("fa fa-w fa-github"));
        icons_list.add(new icons_class("fa fa-w fa-github-alt"));
        icons_list.add(new icons_class("fa fa-w fa-github-square"));
        icons_list.add(new icons_class("fa fa-w fa-gittip"));
        icons_list.add(new icons_class("fa fa-w fa-glass"));
        icons_list.add(new icons_class("fa fa-w fa-globe"));
        icons_list.add(new icons_class("fa fa-w fa-google"));
        icons_list.add(new icons_class("fa fa-w fa-google-plus"));
        icons_list.add(new icons_class("fa fa-w fa-google-plus-square"));
        icons_list.add(new icons_class("fa fa-w fa-google-wallet"));
        icons_list.add(new icons_class("fa fa-w fa-graduation-cap"));
        icons_list.add(new icons_class("fa fa-w fa-group"));
        icons_list.add(new icons_class("fa fa-w fa-h-square"));
        icons_list.add(new icons_class("fa fa-w fa-hacker-news"));
        icons_list.add(new icons_class("fa fa-w fa-hand-o-down"));
        icons_list.add(new icons_class("fa fa-w fa-hand-o-left"));
        icons_list.add(new icons_class("fa fa-w fa-hand-o-right"));
        icons_list.add(new icons_class("fa fa-w fa-hand-o-up"));
        icons_list.add(new icons_class("fa fa-w fa-hdd-o"));
        icons_list.add(new icons_class("fa fa-w fa-header"));
        icons_list.add(new icons_class("fa fa-w fa-headphones"));
        icons_list.add(new icons_class("fa fa-w fa-heart"));
        icons_list.add(new icons_class("fa fa-w fa-heart-o"));
        icons_list.add(new icons_class("fa fa-w fa-history"));
        icons_list.add(new icons_class("fa fa-w fa-home"));
        icons_list.add(new icons_class("fa fa-w fa-hospital-o"));
        icons_list.add(new icons_class("fa fa-w fa-html5"));
        icons_list.add(new icons_class("fa fa-w fa-ils"));
        icons_list.add(new icons_class("fa fa-w fa-image"));
        icons_list.add(new icons_class("fa fa-w fa-inbox"));
        icons_list.add(new icons_class("fa fa-w fa-indent"));
        icons_list.add(new icons_class("fa fa-w fa-info"));
        icons_list.add(new icons_class("fa fa-w fa-info-circle"));
        icons_list.add(new icons_class("fa fa-w fa-inr"));
        icons_list.add(new icons_class("fa fa-w fa-instagram"));
        icons_list.add(new icons_class("fa fa-w fa-institution"));
        icons_list.add(new icons_class("fa fa-w fa-ioxhost"));
        icons_list.add(new icons_class("fa fa-w fa-italic"));
        icons_list.add(new icons_class("fa fa-w fa-joomla"));
        icons_list.add(new icons_class("fa fa-w fa-jpy"));
        icons_list.add(new icons_class("fa fa-w fa-jsfiddle"));
        icons_list.add(new icons_class("fa fa-w fa-key"));
        icons_list.add(new icons_class("fa fa-w fa-keyboard-o"));
        icons_list.add(new icons_class("fa fa-w fa-krw"));
        icons_list.add(new icons_class("fa fa-w fa-language"));
        icons_list.add(new icons_class("fa fa-w fa-laptop"));
        icons_list.add(new icons_class("fa fa-w fa-lastfm"));
        icons_list.add(new icons_class("fa fa-w fa-lastfm-square"));
        icons_list.add(new icons_class("fa fa-w fa-leaf"));
        icons_list.add(new icons_class("fa fa-w fa-legal"));
        icons_list.add(new icons_class("fa fa-w fa-lemon-o"));
        icons_list.add(new icons_class("fa fa-w fa-level-down"));
        icons_list.add(new icons_class("fa fa-w fa-level-up"));
        icons_list.add(new icons_class("fa fa-w fa-life-bouy"));
        icons_list.add(new icons_class("fa fa-w fa-life-buoy"));
        icons_list.add(new icons_class("fa fa-w fa-life-ring"));
        icons_list.add(new icons_class("fa fa-w fa-life-saver"));
        icons_list.add(new icons_class("fa fa-w fa-lightbulb-o"));
        icons_list.add(new icons_class("fa fa-w fa-line-chart"));
        icons_list.add(new icons_class("fa fa-w fa-link"));
        icons_list.add(new icons_class("fa fa-w fa-linkedin"));
        icons_list.add(new icons_class("fa fa-w fa-linkedin-square"));
        icons_list.add(new icons_class("fa fa-w fa-linux"));
        icons_list.add(new icons_class("fa fa-w fa-list"));
        icons_list.add(new icons_class("fa fa-w fa-list-alt"));
        icons_list.add(new icons_class("fa fa-w fa-list-ol"));
        icons_list.add(new icons_class("fa fa-w fa-list-ul"));
        icons_list.add(new icons_class("fa fa-w fa-location-arrow"));
        icons_list.add(new icons_class("fa fa-w fa-lock"));
        icons_list.add(new icons_class("fa fa-w fa-long-arrow-down"));
        icons_list.add(new icons_class("fa fa-w fa-long-arrow-left"));
        icons_list.add(new icons_class("fa fa-w fa-long-arrow-right"));
        icons_list.add(new icons_class("fa fa-w fa-long-arrow-up"));
        icons_list.add(new icons_class("fa fa-w fa-magic"));
        icons_list.add(new icons_class("fa fa-w fa-magnet"));
        icons_list.add(new icons_class("fa fa-w fa-mail-forward"));
        icons_list.add(new icons_class("fa fa-w fa-mail-reply"));
        icons_list.add(new icons_class("fa fa-w fa-mail-reply-all"));
        icons_list.add(new icons_class("fa fa-w fa-male"));
        icons_list.add(new icons_class("fa fa-w fa-map-marker"));
        icons_list.add(new icons_class("fa fa-w fa-maxcdn"));
        icons_list.add(new icons_class("fa fa-w fa-meanpath"));
        icons_list.add(new icons_class("fa fa-w fa-medkit"));
        icons_list.add(new icons_class("fa fa-w fa-meh-o"));
        icons_list.add(new icons_class("fa fa-w fa-microphone"));
        icons_list.add(new icons_class("fa fa-w fa-microphone-slash"));
        icons_list.add(new icons_class("fa fa-w fa-minus"));
        icons_list.add(new icons_class("fa fa-w fa-minus-circle"));
        icons_list.add(new icons_class("fa fa-w fa-minus-square"));
        icons_list.add(new icons_class("fa fa-w fa-minus-square-o"));
        icons_list.add(new icons_class("fa fa-w fa-mobile"));
        icons_list.add(new icons_class("fa fa-w fa-mobile-phone"));
        icons_list.add(new icons_class("fa fa-w fa-money"));
        icons_list.add(new icons_class("fa fa-w fa-moon-o"));
        icons_list.add(new icons_class("fa fa-w fa-mortar-board"));
        icons_list.add(new icons_class("fa fa-w fa-music"));
        icons_list.add(new icons_class("fa fa-w fa-navicon"));
        icons_list.add(new icons_class("fa fa-w fa-newspaper-o"));
        icons_list.add(new icons_class("fa fa-w fa-openid"));
        icons_list.add(new icons_class("fa fa-w fa-outdent"));
        icons_list.add(new icons_class("fa fa-w fa-pagelines"));
        icons_list.add(new icons_class("fa fa-w fa-paint-brush"));
        icons_list.add(new icons_class("fa fa-w fa-paper-plane"));
        icons_list.add(new icons_class("fa fa-w fa-paper-plane-o"));
        icons_list.add(new icons_class("fa fa-w fa-paperclip"));
        icons_list.add(new icons_class("fa fa-w fa-paragraph"));
        icons_list.add(new icons_class("fa fa-w fa-paste"));
        icons_list.add(new icons_class("fa fa-w fa-pause"));
        icons_list.add(new icons_class("fa fa-w fa-paw"));
        icons_list.add(new icons_class("fa fa-w fa-paypal"));
        icons_list.add(new icons_class("fa fa-w fa-pencil"));
        icons_list.add(new icons_class("fa fa-w fa-pencil-square"));
        icons_list.add(new icons_class("fa fa-w fa-pencil-square-o"));
        icons_list.add(new icons_class("fa fa-w fa-phone"));
        icons_list.add(new icons_class("fa fa-w fa-phone-square"));
        icons_list.add(new icons_class("fa fa-w fa-photo"));
        icons_list.add(new icons_class("fa fa-w fa-picture-o"));
        icons_list.add(new icons_class("fa fa-w fa-pie-chart"));
        icons_list.add(new icons_class("fa fa-w fa-pied-piper"));
        icons_list.add(new icons_class("fa fa-w fa-pied-piper-alt"));
        icons_list.add(new icons_class("fa fa-w fa-pinterest"));
        icons_list.add(new icons_class("fa fa-w fa-pinterest-square"));
        icons_list.add(new icons_class("fa fa-w fa-plane"));
        icons_list.add(new icons_class("fa fa-w fa-play"));
        icons_list.add(new icons_class("fa fa-w fa-play-circle"));
        icons_list.add(new icons_class("fa fa-w fa-play-circle-o"));
        icons_list.add(new icons_class("fa fa-w fa-plug"));
        icons_list.add(new icons_class("fa fa-w fa-plus"));
        icons_list.add(new icons_class("fa fa-w fa-plus-circle"));
        icons_list.add(new icons_class("fa fa-w fa-plus-square"));
        icons_list.add(new icons_class("fa fa-w fa-plus-square-o"));
        icons_list.add(new icons_class("fa fa-w fa-power-off"));
        icons_list.add(new icons_class("fa fa-w fa-print"));
        icons_list.add(new icons_class("fa fa-w fa-puzzle-piece"));
        icons_list.add(new icons_class("fa fa-w fa-qq"));
        icons_list.add(new icons_class("fa fa-w fa-qrcode"));
        icons_list.add(new icons_class("fa fa-w fa-question"));
        icons_list.add(new icons_class("fa fa-w fa-question-circle"));
        icons_list.add(new icons_class("fa fa-w fa-quote-left"));
        icons_list.add(new icons_class("fa fa-w fa-quote-right"));
        icons_list.add(new icons_class("fa fa-w fa-ra"));
        icons_list.add(new icons_class("fa fa-w fa-random"));
        icons_list.add(new icons_class("fa fa-w fa-rebel"));
        icons_list.add(new icons_class("fa fa-w fa-recycle"));
        icons_list.add(new icons_class("fa fa-w fa-reddit"));
        icons_list.add(new icons_class("fa fa-w fa-reddit-square"));
        icons_list.add(new icons_class("fa fa-w fa-refresh"));
        icons_list.add(new icons_class("fa fa-w fa-remove"));
        icons_list.add(new icons_class("fa fa-w fa-renren"));
        icons_list.add(new icons_class("fa fa-w fa-reorder"));
        icons_list.add(new icons_class("fa fa-w fa-repeat"));
        icons_list.add(new icons_class("fa fa-w fa-reply"));
        icons_list.add(new icons_class("fa fa-w fa-reply-all"));
        icons_list.add(new icons_class("fa fa-w fa-retweet"));
        icons_list.add(new icons_class("fa fa-w fa-rmb"));
        icons_list.add(new icons_class("fa fa-w fa-road"));
        icons_list.add(new icons_class("fa fa-w fa-rocket"));
        icons_list.add(new icons_class("fa fa-w fa-rotate-left"));
        icons_list.add(new icons_class("fa fa-w fa-rotate-right"));
        icons_list.add(new icons_class("fa fa-w fa-rouble"));
        icons_list.add(new icons_class("fa fa-w fa-rss"));
        icons_list.add(new icons_class("fa fa-w fa-rss-square"));
        icons_list.add(new icons_class("fa fa-w fa-rub"));
        icons_list.add(new icons_class("fa fa-w fa-ruble"));
        icons_list.add(new icons_class("fa fa-w fa-rupee"));
        icons_list.add(new icons_class("fa fa-w fa-save"));
        icons_list.add(new icons_class("fa fa-w fa-scissors"));
        icons_list.add(new icons_class("fa fa-w fa-search"));
        icons_list.add(new icons_class("fa fa-w fa-search-minus"));
        icons_list.add(new icons_class("fa fa-w fa-search-plus"));
        icons_list.add(new icons_class("fa fa-w fa-send"));
        icons_list.add(new icons_class("fa fa-w fa-send-o"));
        icons_list.add(new icons_class("fa fa-w fa-share"));
        icons_list.add(new icons_class("fa fa-w fa-share-alt"));
        icons_list.add(new icons_class("fa fa-w fa-share-alt-square"));
        icons_list.add(new icons_class("fa fa-w fa-share-square"));
        icons_list.add(new icons_class("fa fa-w fa-share-square-o"));
        icons_list.add(new icons_class("fa fa-w fa-shekel"));
        icons_list.add(new icons_class("fa fa-w fa-sheqel"));
        icons_list.add(new icons_class("fa fa-w fa-shield"));
        icons_list.add(new icons_class("fa fa-w fa-shopping-cart"));
        icons_list.add(new icons_class("fa fa-w fa-sign-in"));
        icons_list.add(new icons_class("fa fa-w fa-sign-out"));
        icons_list.add(new icons_class("fa fa-w fa-signal"));
        icons_list.add(new icons_class("fa fa-w fa-sitemap"));
        icons_list.add(new icons_class("fa fa-w fa-skype"));
        icons_list.add(new icons_class("fa fa-w fa-slack"));
        icons_list.add(new icons_class("fa fa-w fa-sliders"));
        icons_list.add(new icons_class("fa fa-w fa-slideshare"));
        icons_list.add(new icons_class("fa fa-w fa-smile-o"));
        icons_list.add(new icons_class("fa fa-w fa-soccer-ball-o"));
        icons_list.add(new icons_class("fa fa-w fa-sort"));
        icons_list.add(new icons_class("fa fa-w fa-sort-alpha-asc"));
        icons_list.add(new icons_class("fa fa-w fa-sort-alpha-desc"));
        icons_list.add(new icons_class("fa fa-w fa-sort-amount-asc"));
        icons_list.add(new icons_class("fa fa-w fa-sort-amount-desc"));
        icons_list.add(new icons_class("fa fa-w fa-sort-asc"));
        icons_list.add(new icons_class("fa fa-w fa-sort-desc"));
        icons_list.add(new icons_class("fa fa-w fa-sort-down"));
        icons_list.add(new icons_class("fa fa-w fa-sort-numeric-asc"));
        icons_list.add(new icons_class("fa fa-w fa-sort-numeric-desc"));
        icons_list.add(new icons_class("fa fa-w fa-sort-up"));
        icons_list.add(new icons_class("fa fa-w fa-soundcloud"));
        icons_list.add(new icons_class("fa fa-w fa-space-shuttle"));
        icons_list.add(new icons_class("fa fa-w fa-spinner"));
        icons_list.add(new icons_class("fa fa-w fa-spoon"));
        icons_list.add(new icons_class("fa fa-w fa-spotify"));
        icons_list.add(new icons_class("fa fa-w fa-square"));
        icons_list.add(new icons_class("fa fa-w fa-square-o"));
        icons_list.add(new icons_class("fa fa-w fa-stack-exchange"));
        icons_list.add(new icons_class("fa fa-w fa-stack-overflow"));
        icons_list.add(new icons_class("fa fa-w fa-star"));
        icons_list.add(new icons_class("fa fa-w fa-star-half"));
        icons_list.add(new icons_class("fa fa-w fa-star-half-empty"));
        icons_list.add(new icons_class("fa fa-w fa-star-half-full"));
        icons_list.add(new icons_class("fa fa-w fa-star-half-o"));
        icons_list.add(new icons_class("fa fa-w fa-star-o"));
        icons_list.add(new icons_class("fa fa-w fa-steam"));
        icons_list.add(new icons_class("fa fa-w fa-steam-square"));
        icons_list.add(new icons_class("fa fa-w fa-step-backward"));
        icons_list.add(new icons_class("fa fa-w fa-step-forward"));
        icons_list.add(new icons_class("fa fa-w fa-stethoscope"));
        icons_list.add(new icons_class("fa fa-w fa-stop"));
        icons_list.add(new icons_class("fa fa-w fa-strikethrough"));
        icons_list.add(new icons_class("fa fa-w fa-stumbleupon"));
        icons_list.add(new icons_class("fa fa-w fa-stumbleupon-circle"));
        icons_list.add(new icons_class("fa fa-w fa-subscript"));
        icons_list.add(new icons_class("fa fa-w fa-suitcase"));
        icons_list.add(new icons_class("fa fa-w fa-sun-o"));
        icons_list.add(new icons_class("fa fa-w fa-superscript"));
        icons_list.add(new icons_class("fa fa-w fa-support"));
        icons_list.add(new icons_class("fa fa-w fa-table"));
        icons_list.add(new icons_class("fa fa-w fa-tablet"));
        icons_list.add(new icons_class("fa fa-w fa-tachometer"));
        icons_list.add(new icons_class("fa fa-w fa-tag"));
        icons_list.add(new icons_class("fa fa-w fa-tags"));
        icons_list.add(new icons_class("fa fa-w fa-tasks"));
        icons_list.add(new icons_class("fa fa-w fa-taxi"));
        icons_list.add(new icons_class("fa fa-w fa-tencent-weibo"));
        icons_list.add(new icons_class("fa fa-w fa-terminal"));
        icons_list.add(new icons_class("fa fa-w fa-text-height"));
        icons_list.add(new icons_class("fa fa-w fa-text-width"));
        icons_list.add(new icons_class("fa fa-w fa-th"));
        icons_list.add(new icons_class("fa fa-w fa-th-large"));
        icons_list.add(new icons_class("fa fa-w fa-th-list"));
        icons_list.add(new icons_class("fa fa-w fa-thumb-tack"));
        icons_list.add(new icons_class("fa fa-w fa-thumbs-down"));
        icons_list.add(new icons_class("fa fa-w fa-thumbs-o-down"));
        icons_list.add(new icons_class("fa fa-w fa-thumbs-o-up"));
        icons_list.add(new icons_class("fa fa-w fa-thumbs-up"));
        icons_list.add(new icons_class("fa fa-w fa-ticket"));
        icons_list.add(new icons_class("fa fa-w fa-times"));
        icons_list.add(new icons_class("fa fa-w fa-times-circle"));
        icons_list.add(new icons_class("fa fa-w fa-times-circle-o"));
        icons_list.add(new icons_class("fa fa-w fa-tint"));
        icons_list.add(new icons_class("fa fa-w fa-toggle-down"));
        icons_list.add(new icons_class("fa fa-w fa-toggle-left"));
        icons_list.add(new icons_class("fa fa-w fa-toggle-off"));
        icons_list.add(new icons_class("fa fa-w fa-toggle-on"));
        icons_list.add(new icons_class("fa fa-w fa-toggle-right"));
        icons_list.add(new icons_class("fa fa-w fa-toggle-up"));
        icons_list.add(new icons_class("fa fa-w fa-trash"));
        icons_list.add(new icons_class("fa fa-w fa-trash-o"));
        icons_list.add(new icons_class("fa fa-w fa-tree"));
        icons_list.add(new icons_class("fa fa-w fa-trello"));
        icons_list.add(new icons_class("fa fa-w fa-trophy"));
        icons_list.add(new icons_class("fa fa-w fa-truck"));
        icons_list.add(new icons_class("fa fa-w fa-try"));
        icons_list.add(new icons_class("fa fa-w fa-tty"));
        icons_list.add(new icons_class("fa fa-w fa-tumblr"));
        icons_list.add(new icons_class("fa fa-w fa-tumblr-square"));
        icons_list.add(new icons_class("fa fa-w fa-turkish-lira"));
        icons_list.add(new icons_class("fa fa-w fa-twitch"));
        icons_list.add(new icons_class("fa fa-w fa-twitter"));
        icons_list.add(new icons_class("fa fa-w fa-twitter-square"));
        icons_list.add(new icons_class("fa fa-w fa-umbrella"));
        icons_list.add(new icons_class("fa fa-w fa-underline"));
        icons_list.add(new icons_class("fa fa-w fa-undo"));
        icons_list.add(new icons_class("fa fa-w fa-university"));
        icons_list.add(new icons_class("fa fa-w fa-unlink"));
        icons_list.add(new icons_class("fa fa-w fa-unlock"));
        icons_list.add(new icons_class("fa fa-w fa-unlock-alt"));
        icons_list.add(new icons_class("fa fa-w fa-unsorted"));
        icons_list.add(new icons_class("fa fa-w fa-upload"));
        icons_list.add(new icons_class("fa fa-w fa-usd"));
        icons_list.add(new icons_class("fa fa-w fa-user"));
        icons_list.add(new icons_class("fa fa-w fa-user-md"));
        icons_list.add(new icons_class("fa fa-w fa-users"));
        icons_list.add(new icons_class("fa fa-w fa-video-camera"));
        icons_list.add(new icons_class("fa fa-w fa-vimeo-square"));
        icons_list.add(new icons_class("fa fa-w fa-vine"));
        icons_list.add(new icons_class("fa fa-w fa-vk"));
        icons_list.add(new icons_class("fa fa-w fa-volume-down"));
        icons_list.add(new icons_class("fa fa-w fa-volume-off"));
        icons_list.add(new icons_class("fa fa-w fa-volume-up"));
        icons_list.add(new icons_class("fa fa-w fa-warning"));
        icons_list.add(new icons_class("fa fa-w fa-wechat"));
        icons_list.add(new icons_class("fa fa-w fa-weibo"));
        icons_list.add(new icons_class("fa fa-w fa-weixin"));
        icons_list.add(new icons_class("fa fa-w fa-wheelchair"));
        icons_list.add(new icons_class("fa fa-w fa-wifi"));
        icons_list.add(new icons_class("fa fa-w fa-windows"));
        icons_list.add(new icons_class("fa fa-w fa-won"));
        icons_list.add(new icons_class("fa fa-w fa-wordpress"));
        icons_list.add(new icons_class("fa fa-w fa-wrench"));
        icons_list.add(new icons_class("fa fa-w fa-xing"));
        icons_list.add(new icons_class("fa fa-w fa-xing-square"));
        icons_list.add(new icons_class("fa fa-w fa-yahoo"));
        icons_list.add(new icons_class("fa fa-w fa-yelp"));
        icons_list.add(new icons_class("fa fa-w fa-yen"));
        icons_list.add(new icons_class("fa fa-w fa-youtube"));
        icons_list.add(new icons_class("fa fa-w fa-youtube-play"));
        icons_list.add(new icons_class("fa fa-w fa-youtube-square"));

        }

    public void edit_menu_item(String item_id)
        {
        this.item_id = item_id;
        item_title = "";
        item_url = "";
        item_icon = "";
        connect conn = new connect();
        ResultSet rs = null;
        try
            {
            rs = conn.query("select item_title as item_title,item_url as item_url,icon as item_icon from menu_items where item_id=" + item_id);
            while (rs.next())
                {
                item_title = rs.getString("item_title");
                item_url = rs.getString("item_url");
                item_icon = rs.getString("item_icon");
                }
            }
        catch (SQLException sqlEx)
            {

            } finally
            {
            try
                {
                if (rs != null)
                    {
                    rs.getStatement().close();
                    rs.close();
                    }
                }
            catch (SQLException sqlEx)
                {
                }
            }
        conn.close();

        common_functions.getInstance().open_dialog("icons", true, 800, 600, false, false, true, true);
        }

    public void save()
        {
        connect conn = new connect();
        
        try
            {
            conn.update("update menu_items set ITEM_TITLE=?,ITEM_URL=?,ICON=? where item_id=?", new String[]
                {
                item_title, item_url, item_icon, item_id
                });
            msgr.info("Menu item has been updated successfully........");
            }
        catch (Exception e)
            {
            msgr.error("Error:" + e.getMessage());
            }
        conn.close();

        }

    public void setIcons_list(List<Icons.icons_class> icons_list)
        {
        this.icons_list = icons_list;
        }

    public List<Icons.icons_class> getIcons_list()
        {
        return icons_list;
        }

    public void setItem_id(String item_id)
        {
        this.item_id = item_id;
        }

    public String getItem_id()
        {
        return item_id;
        }

    public void setItem_title(String item_title)
        {
        this.item_title = item_title;
        }

    public String getItem_title()
        {
        return item_title;
        }

    public void setItem_url(String item_url)
        {
        this.item_url = item_url;
        }

    public String getItem_url()
        {
        return item_url;
        }

    public void setItem_icon(String item_icon)
        {
        this.item_icon = item_icon;
        }

    public String getItem_icon()
        {
        return item_icon;
        }

    public class icons_class
        {
        String styleclass;

        public icons_class(String styleclass)
            {
            this.styleclass = styleclass;
            }

        public void set_item_icon()
            {
            item_icon = this.styleclass;
            }

        public void setStyleclass(String styleclass)
            {
            this.styleclass = styleclass;
            }

        public String getStyleclass()
            {
            return styleclass;
            }
        }
    }
